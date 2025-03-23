package com.study.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.data.model.NewsDetail
import com.study.data.repository.FavoriteRepository
import com.study.detail.navigation.ITEM_ID
import com.study.domain.GetDetailUseCase
import com.study.domain.SaveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    val getDetailUseCase: GetDetailUseCase,
    val favoriteRepository: FavoriteRepository,
    val saveFavoriteUseCase: SaveFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val detailScreenUiState = MutableStateFlow<DetailScreenUIState>(DetailScreenUIState.Loading)
    val itemId = savedStateHandle[ITEM_ID] ?: 0

    init {
        initValues()
    }

    fun initValues(){
        viewModelScope.launch {
            Log.d("DetailScreenViewModel", "Get Detail Item Id: ${itemId}")
            getDetailUseCase.invoke(itemId)
                .catch {
                    Log.e("DetailScreenViewModel", "Get Detail Error: ${it.message}")
                    detailScreenUiState.value = DetailScreenUIState.Detail(errorMessage = "Can not get the details of the news")
                }
                .collect { item ->
                    favoriteRepository.isItemLiked(item.id)
                        .collect { isSaved ->
                            item.isSaved = isSaved
                            detailScreenUiState.value = DetailScreenUIState.Detail(item)
                        }

                }
        }
    }

    fun onFavoriteClicked(newsDetail: NewsDetail) {
        viewModelScope.launch {
            saveFavoriteUseCase.invoke(newsDetail)
                .catch {
                    Log.e("DetailScreenViewModel", "Save Favorite Error: ${it.message}")
                }
                .collect {
                    Log.i("DetailScreenViewModel", "Save Favorite Success}")
                }
        }
    }
}