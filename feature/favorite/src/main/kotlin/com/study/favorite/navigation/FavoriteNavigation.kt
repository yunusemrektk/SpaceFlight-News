package com.study.favorite.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.study.favorite.FavoriteRoute

const val FAVORITE_ROUTE = "favorite_screen_route"

fun NavController.navigateToFavorite(navOptions: NavOptions) {
    this.navigate(route = FAVORITE_ROUTE, navOptions)
}

fun NavGraphBuilder.favoriteScreen(
    onBackClick: () -> Unit,
) {
    composable(route = FAVORITE_ROUTE) {
        BackHandler(true) { onBackClick() }
        FavoriteRoute()
    }
}