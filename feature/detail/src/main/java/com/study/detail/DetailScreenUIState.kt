package com.study.detail

import com.study.data.model.NewsDetail

sealed class DetailScreenUIState {
    data class Detail(val detail: NewsDetail = NewsDetail(), val errorMessage: String = "") : DetailScreenUIState()
    data object Loading : DetailScreenUIState()
}

