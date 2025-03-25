package com.study.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.data.repository.OfflineUserDataRepository
import com.study.domain.RemoveFavoriteUseCase
import com.study.model.NewsDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    val removeFavoriteUseCase: RemoveFavoriteUseCase,
    val offlineUserDataRepository: OfflineUserDataRepository
    ) :ViewModel(){
    val favoriteScreenUiState = MutableStateFlow<FavoriteScreenUiState>(FavoriteScreenUiState.Loading)

    init {
        getFavorites()
    }

    fun getFavorites() {
        viewModelScope.launch {
            offlineUserDataRepository.observeAllFavorites()
                .map<List<NewsDetail>, FavoriteScreenUiState>(FavoriteScreenUiState::ListView)
                .onStart { emit(FavoriteScreenUiState.Loading) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(3_000),
                    initialValue = FavoriteScreenUiState.Loading,
                ).collect { collectResult ->
                    when (val state = collectResult) {

                        is FavoriteScreenUiState.ListView -> {
                            favoriteScreenUiState.update {
                                FavoriteScreenUiState.ListView(state.likedNews)
                            }
                        }

                        else -> {

                        }
                    }
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