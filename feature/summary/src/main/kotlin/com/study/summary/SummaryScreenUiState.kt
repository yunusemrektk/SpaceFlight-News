package com.study.summary


import com.study.model.NewsSummary

sealed class SummaryScreenUiState {
    data class Summary(
        val newsSummary: List<NewsSummary> = emptyList(),
        val errorMessage: String = "",
        val isRefreshing: Boolean = false
    ) : SummaryScreenUiState()
}
