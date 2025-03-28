package com.study.detail

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
    onLikeClick: (NewsDetail, Boolean) -> Unit
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
                    article = detailScreenUIState.detail.article,
                    imageUrl = detailScreenUIState.detail.image,
                    date = detailScreenUIState.detail.date,
                    isSaved = detailScreenUIState.detail.isSaved,
                    errorMessage = detailScreenUIState.errorMessage,
                    onBackClick = onBackClick,
                    onLikeClick = { isLiked ->
                        onLikeClick(detailScreenUIState.detail, isLiked)
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
    onLikeClick: (Boolean) -> Unit
) {
    DetailItemScreen(
        title = title,
        article = article,
        imageUrl = imageUrl,
        date = date,
        isSaved = isSaved,
        onBackClick = onBackClick,
        onLikeClick = onLikeClick,
    )

    if (errorMessage.isNotEmpty()) {
        ErrorScreen(
            errorMessage = errorMessage,
            onDismiss = onBackClick
        )
    }
}

class DetailParameterProvider : PreviewParameterProvider<DetailScreenUIState> {
    val new1 = NewsDetail(
        id = 0,
        title = "Not Just for Engineers: Broadening the Space Pipeline",
        article = "nIn this week's episode of Space Minds, Sara Alvarado, Executive Director for the Students for the Exploration and Development of Space, known as SEDS, sits down with host David Ariosto.\\nThe post Not Just for Engineers: Broadening the Space Pipeline appeared first on SpaceNews.",
        image = "https://i0.wp.com/spacenews.com/wp-content/uploads/2025/03/2000x1500-Sara-Alvarado.png?fit=1024%2C768&quality=80&ssl=1",
        date = "today",
        isSaved = true
    )


    override val values: Sequence<DetailScreenUIState> = sequenceOf(
        DetailScreenUIState.Detail(new1)
    )

}

@Preview
@Composable
fun PreviewFavoriteScreen(
    @PreviewParameter(DetailParameterProvider::class) parameter: DetailScreenUIState
) {
    DetailScreen(
        detailScreenUIState = parameter,
        onBackClick = {},
        onLikeClick = { id, isLiked -> {} }
    )
}
