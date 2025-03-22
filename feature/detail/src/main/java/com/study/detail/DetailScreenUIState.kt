package com.study.detail

import androidx.compose.ui.graphics.vector.ImageVector

sealed class DetailScreenUIState {
    data class Detail(val detail: NewsDetail, val errorMessage: String) : DetailScreenUIState()
    data object Loading : DetailScreenUIState()
}

data class NewsDetail(
    val title: String = "",
    val article: String = "",
    val date: String = "",
    val image: ImageVector
)