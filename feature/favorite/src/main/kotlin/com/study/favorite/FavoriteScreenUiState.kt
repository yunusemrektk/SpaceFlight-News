package com.study.favorite

import com.study.model.NewsDetail

sealed interface FavoriteScreenUiState {
    data class ListView(val likedNews: List<NewsDetail> = emptyList()) : FavoriteScreenUiState
    data class Detail(val detail: NewsDetail) : FavoriteScreenUiState
    data object Loading : FavoriteScreenUiState
}
