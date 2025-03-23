package com.study.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.data.model.NewsDetail
import com.study.domain.GetFavoritesUseCase
import com.study.domain.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    val getFavoritesUseCase: GetFavoritesUseCase,
    val removeFavoriteUseCase: RemoveFavoriteUseCase,
    ) :ViewModel(){
    val favoriteScreenUiState = MutableStateFlow<FavoriteScreenUiState>(FavoriteScreenUiState.Loading)

    init {
        getFavorites()
    }

    fun getFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase.invoke()
                .catch {
                    Log.e("FavoriteScreenViewModel", "Get Favorite Error: ${it.message}")
                    favoriteScreenUiState.value = FavoriteScreenUiState.ListView(errorMessage = "Error happened while getting favorite list")
                }
                .collect { list->
                    Log.i("FavoriteScreenViewModel", "Get Favorites Success list size: ${list.size}")
                    favoriteScreenUiState.value = FavoriteScreenUiState.ListView(likedNews = list)
            }
        }
    }

    fun onLikeClicked(id: Int) {
        viewModelScope.launch {
            removeFavoriteUseCase.invoke(id)
                .catch {
                    Log.i("FavoriteScreenViewModel", "Remove Favorite Error id: ${id}")
                }
                .collect {
                    Log.i("FavoriteScreenViewModel", "Remove Favorite Success")
                }
        }
    }

    fun onBackClicked() {
        getFavorites()
    }

    fun onDetailClicked(newsDetail: NewsDetail) {
        viewModelScope.launch {
            favoriteScreenUiState.value = FavoriteScreenUiState.Detail(newsDetail)
        }
    }
}