package com.study.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.domain.GetFavoritesUseCase
import com.study.domain.RemoveFavoriteUseCase
import com.study.model.NewsDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    val removeFavoriteUseCase: RemoveFavoriteUseCase,
    val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {
    val favoriteScreenUiState =
        MutableStateFlow<FavoriteScreenUiState>(FavoriteScreenUiState.Loading)

    fun getFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase.invoke()
                .map<List<NewsDetail>, FavoriteScreenUiState>(FavoriteScreenUiState::ListView)
                .onStart { emit(FavoriteScreenUiState.Loading) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(3_000),
                    initialValue = FavoriteScreenUiState.Loading,
                ).collect { collectResult ->
                    Log.i("GetFavoritesUseCase", "Get Favorites UseCase result: $collectResult")
                    favoriteScreenUiState.value = collectResult
                }
        }
    }

    fun onRemoveFromFavoriteList(newsDetail: NewsDetail) {
        viewModelScope.launch {
            removeFavoriteUseCase.invoke(newsDetail)
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