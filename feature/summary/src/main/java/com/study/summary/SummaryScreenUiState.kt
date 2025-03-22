package com.study.summary

data class SummaryScreenUiState(
    val newsSummary: List<NewsSummary> = emptyList(),
    val errorMessage: String = "",
    val isLoading :Boolean = false
    )

data class NewsSummary(
    val title: String = "",
    val summary: String = "",
    val date: String = "",

)

