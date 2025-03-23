package com.study.favorite

import com.study.data.model.NewsDetail

sealed class FavoriteScreenUiState {
    data class ListView(val likedNews: List<NewsDetail> = emptyList(), val errorMessage: String = ""): FavoriteScreenUiState()
    data class Detail(val detail: NewsDetail): FavoriteScreenUiState()
    data object Loading : FavoriteScreenUiState()
}