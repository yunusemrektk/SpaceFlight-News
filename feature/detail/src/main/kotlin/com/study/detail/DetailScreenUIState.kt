package com.study.detail

import com.study.model.NewsDetail

sealed class DetailScreenUIState {
    data class Detail(val detail: NewsDetail = NewsDetail(), val errorMessage: String = "") :
        DetailScreenUIState()

    data object Loading : DetailScreenUIState()
}

