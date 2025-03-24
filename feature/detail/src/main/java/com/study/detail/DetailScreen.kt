package com.study.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.study.model.NewsDetail
import com.study.ui.DetailItemScreen
import com.study.ui.ErrorScreen
import com.study.ui.LoadingScreen

@Composable
fun DetailRoute(
    onBackClick: () -> Unit,
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
) {
    val detailScreenUiState by detailScreenViewModel.detailScreenUiState.collectAsStateWithLifecycle()
    DetailScreen(
        detailScreenUiState,
        onBackClick,
        detailScreenViewModel::onFavoriteClicked
    )
}

@Composable
fun DetailScreen(
    detailScreenUIState: DetailScreenUIState,
    onBackClick: () -> Unit,
    onLikeClick: (NewsDetail) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (detailScreenUIState) {
            is DetailScreenUIState.Loading -> {
                LoadingScreen()
            }

            is DetailScreenUIState.Detail -> {
                DetailScreen(
                    title = detailScreenUIState.detail.title,
                    article =  detailScreenUIState.detail.article,
                    imageUrl = detailScreenUIState.detail.image,
                    date = detailScreenUIState.detail.date,
                    isSaved = detailScreenUIState.detail.isSaved,
                    errorMessage = detailScreenUIState.errorMessage,
                    onBackClick = onBackClick,
                    onLikeClick = {
                        onLikeClick(detailScreenUIState.detail)
                    }
                )

            }
        }

    }
}

@Composable
fun DetailScreen(
    title: String,
    article: String,
    imageUrl: String,
    date: String,
    isSaved: Boolean,
    errorMessage: String,
    onBackClick: () -> Unit,
    onLikeClick: () -> Unit
) {
    DetailItemScreen(
        title = title,
        article =  article,
        imageUrl = imageUrl,
        date = date,
        isSaved = isSaved,
        onBackClick = onBackClick,
        onLikeClick = onLikeClick
    )

    if(errorMessage.isNotEmpty()) {
        ErrorScreen(
            errorMessage = errorMessage,
            onDismiss = onBackClick
        )
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    val detailScreenUIState = DetailScreenUIState.Detail(NewsDetail(), "")
    DetailScreen(detailScreenUIState = detailScreenUIState, onBackClick = {} , onLikeClick = {})
}