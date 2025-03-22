package com.study.main


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.study.ui.LoadingScreen

@Composable
fun MainRoute(
    onBackClick: () -> Unit,
    onNavigateToSummaryScreen: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val mainScreenUiState by mainScreenViewModel.mainScreenUiState.collectAsStateWithLifecycle()
    MainScreen(
        onNavigateToSummaryScreen = onNavigateToSummaryScreen,
        onNavigateToFavorites = onNavigateToFavorites,
        mainScreenUiState
    )
}

@Composable
fun MainScreen(
    onNavigateToSummaryScreen: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    mainScreenUiState: MainScreenUiState)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (mainScreenUiState) {
            is MainScreenUiState.Loading -> {
                LoadingScreen()
            }
            is MainScreenUiState.Favorite -> {
                onNavigateToFavorites()
            }

            is MainScreenUiState.Summary -> {
                onNavigateToSummaryScreen()
            }
        }
    }
}

class MainScreenContentProvider : PreviewParameterProvider<MainScreenUiState> {

    override val values = sequenceOf(

        MainScreenUiState.Loading,

    )
}

@Preview
@Composable
fun PrevMainScreen(
    @PreviewParameter(MainScreenContentProvider::class) content: MainScreenUiState
) {
    //MainScreen(onDetailClick = {},content)
}