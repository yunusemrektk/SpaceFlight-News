package com.study.summary

import com.study.data.model.NewsSummary

sealed class SummaryScreenUiState {
    data class Summary(val newsSummary: List<NewsSummary> = emptyList(), val errorMessage: String = "") : SummaryScreenUiState()
    data object Loading : SummaryScreenUiState()
}