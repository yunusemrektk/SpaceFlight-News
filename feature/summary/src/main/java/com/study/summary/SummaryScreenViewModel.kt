package com.study.summary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.domain.GetSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryScreenViewModel @Inject constructor(
    val getSummaryUseCase: GetSummaryUseCase
) : ViewModel()
{
    val summaryScreenUiState = MutableStateFlow<SummaryScreenUiState>(SummaryScreenUiState.Loading)

    init {
        getNewsSummaries()
    }

    fun getNewsSummaries() {
        viewModelScope.launch {
             getSummaryUseCase.invoke()
                 .catch { it->
                     Log.e("SummaryScreenViewModel", "Get Summary Error: ${it.message}")
                     summaryScreenUiState.value = SummaryScreenUiState.Summary(errorMessage = "Error on getting news")
                 }
                 .collect { newsSummary->
                     Log.e("SummaryScreenViewModel", "Get Summary Success news size: ${newsSummary.size}")
                     summaryScreenUiState.value = SummaryScreenUiState.Summary(newsSummary = newsSummary)
            }
        }
    }
}