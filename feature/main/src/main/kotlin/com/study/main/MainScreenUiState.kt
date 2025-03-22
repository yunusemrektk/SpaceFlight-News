package com.study.main


sealed class MainScreenUiState {
    data object Summary : MainScreenUiState()
    data object Favorite : MainScreenUiState()
    data object Loading : MainScreenUiState()
}

