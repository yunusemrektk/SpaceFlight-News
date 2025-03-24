package com.study.favorite

import com.study.model.NewsDetail

sealed class FavoriteScreenUiState {
    data class ListView(val likedNews: List<NewsDetail> = emptyList(), val errorMessage: String = ""): FavoriteScreenUiState()
    data class Detail(val detail: NewsDetail): FavoriteScreenUiState()
    data object Loading : FavoriteScreenUiState()
}

sealed interface FavoriteListsUiState{
    data object Loading : FavoriteListsUiState

    data class Success(
        val favorites: List<NewsDetail>,
    ) : FavoriteListsUiState

}