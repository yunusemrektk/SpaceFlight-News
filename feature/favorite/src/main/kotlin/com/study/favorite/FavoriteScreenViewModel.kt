package com.study.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.data.repository.OfflineUserDataRepository
import com.study.domain.GetFavoritesUseCase
import com.study.domain.RemoveFavoriteUseCase
import com.study.model.NewsDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    val getFavoritesUseCase: GetFavoritesUseCase,
    val removeFavoriteUseCase: RemoveFavoriteUseCase,
    val offlineUserDataRepository: OfflineUserDataRepository
    ) :ViewModel(){
    val favoriteScreenUiState = MutableStateFlow<FavoriteScreenUiState>(FavoriteScreenUiState.Loading)

    init {
        getFavorites()
    }

    val favoritesUiState: StateFlow<FavoriteListsUiState> =
        offlineUserDataRepository.observeAllFavorites()
            .map<List<NewsDetail>, FavoriteListsUiState>(FavoriteListsUiState::Success)
            .onStart { emit(FavoriteListsUiState.Loading) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(3_000),
                initialValue = FavoriteListsUiState.Loading,
            )

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

    fun onLikeClicked(newsDetail: NewsDetail) {
        viewModelScope.launch {
            removeFavoriteUseCase.invoke(newsDetail)
                .catch {
                    Log.i("FavoriteScreenViewModel", "Remove Favorite Error id: ${newsDetail.id}")
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