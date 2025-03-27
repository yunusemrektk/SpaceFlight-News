package com.study.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.domain.GetOfflineUserDataUseCase
import com.study.domain.GetSummaryUseCase
import com.study.model.NewsSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class SummaryScreenViewModel @Inject constructor(
    val getSummaryUseCase: GetSummaryUseCase,
    val getOfflineUserDataUseCase: GetOfflineUserDataUseCase
) : ViewModel() {
    val uiState = MutableStateFlow<SummaryScreenUiState>(SummaryScreenUiState.Summary())

    init {
        onRefresh()
    }

    fun onRefresh() {
        viewModelScope.launch {
            getSummaryUseCase.invoke()
                .map<List<NewsSummary>, SummaryScreenUiState>(SummaryScreenUiState::Summary)
                .onStart { onStart() }
                .collect { state ->
                    delay(1000) //TODO pullbox need it to animate loading indicator
                    onOnlineCollect(state)
                }
        }
    }

    fun getOfflineData() {
        viewModelScope.launch {
            delay(1000) // TODO to show error message
            getOfflineUserDataUseCase.invoke()
                .collect { list ->
                    if (list.isEmpty()) {
                        SummaryScreenUiState.Summary(
                            errorMessage = "Error while getting the offline news",
                            isRefreshing = false
                        )
                    } else {
                        uiState.update {
                            SummaryScreenUiState.Summary(
                                newsSummary = list,
                                isRefreshing = false
                            )
                        }
                    }
                }
        }

    }

    private fun onOnlineCollect(state: SummaryScreenUiState) {
        when (state) {
            is SummaryScreenUiState.Summary -> {
                if (state.newsSummary.isEmpty()) {
                    if (uiState.value is SummaryScreenUiState.Summary) {
                        uiState.update {
                            SummaryScreenUiState.Summary(
                                newsSummary = (uiState.value as SummaryScreenUiState.Summary).newsSummary,
                                errorMessage = "Can not update the news",
                                isRefreshing = false
                            )
                        }

                    } else {
                        uiState.update {
                            SummaryScreenUiState.Summary(
                                newsSummary = state.newsSummary,
                                errorMessage = "Can not update the news",
                                isRefreshing = false
                            )
                        }
                    }

                    getOfflineData()
                } else {
                    uiState.update {
                        SummaryScreenUiState.Summary(
                            newsSummary = state.newsSummary,
                            isRefreshing = false
                        )
                    }
                }
            }
        }
    }

    private fun onStart() {
        uiState.update { it ->
            when (it) {
                is SummaryScreenUiState.Summary -> SummaryScreenUiState.Summary(
                    newsSummary = it.newsSummary,
                    errorMessage = "",
                    isRefreshing = true
                )
            }
        }
    }
}