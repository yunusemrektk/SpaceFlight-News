package com.study.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.detail.navigation.ITEM_ID
import com.study.domain.GetOfflineDetailUseCase
import com.study.domain.SaveFavoriteUseCase
import com.study.model.NewsDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    val saveFavoriteUseCase: SaveFavoriteUseCase,
    val getOfflineDetailUseCase: GetOfflineDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val detailScreenUiState = MutableStateFlow<DetailScreenUIState>(DetailScreenUIState.Loading)
    val itemId = savedStateHandle[ITEM_ID] ?: 0

    init {
        initValues()
    }

    fun initValues() {
        viewModelScope.launch {
            getOfflineDetailUseCase.invoke(itemId).collect { item ->
                if (item == null) {
                    detailScreenUiState.value =
                        DetailScreenUIState.Detail(errorMessage = "Can not get the details of the news")
                } else {
                    detailScreenUiState.value = DetailScreenUIState.Detail(item)
                }
            }
        }
    }

    fun onFavoriteClicked(newsDetail: NewsDetail, isLiked: Boolean) {
        viewModelScope.launch {
            saveFavoriteUseCase.invoke(newsDetail, isLiked)
                .catch {
                    Log.e("DetailScreenViewModel", "Save Favorite Error: ${it.message}")
                }
                .collect {
                    Log.i("DetailScreenViewModel", "Save Favorite Success}")
                }
        }
    }
}