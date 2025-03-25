package com.study.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.study.ui.LoadingScreen

@Composable
fun MainRoute(
    onNavigateToSummaryScreen: () -> Unit,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val mainScreenUiState by mainScreenViewModel.mainScreenUiState.collectAsStateWithLifecycle()
    MainScreen(
        onNavigateToSummaryScreen = onNavigateToSummaryScreen,
        mainScreenUiState
    )
}

@Composable
fun MainScreen(
    onNavigateToSummaryScreen: () -> Unit,
    mainScreenUiState: MainScreenUiState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (mainScreenUiState) {
            is MainScreenUiState.Loading -> {
                LoadingScreen()
            }

            is MainScreenUiState.Summary -> {
                onNavigateToSummaryScreen()
            }
        }

    }
}



