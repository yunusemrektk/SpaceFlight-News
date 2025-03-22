package com.study.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoriteRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel()
) {
}