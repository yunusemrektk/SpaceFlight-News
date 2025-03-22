package com.study.spaceflightnewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.study.detail.navigation.detailScreen
import com.study.detail.navigation.navigateToDetail
import com.study.favorite.navigation.favoriteScreen
import com.study.favorite.navigation.navigateToFavorite
import com.study.main.navigation.*
import com.study.summary.navigation.navigateToSummary
import com.study.summary.navigation.summaryScreen

@Composable
fun AppNavigator(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Navigator(
        modifier = modifier,
        navHostController = navHostController,
        startDestination = MAIN_ROUTE
    ) {
        mainScreen(
            onBackClick = {},
            onNavigateToSummaryScreen = {
                navHostController.navigateToSummary()
            },
            onNavigateToFavorites = {
                navHostController.navigateToFavorite()
            }
        )

        favoriteScreen (
            onBackClick = {navHostController.popBackStack()}
        )
        detailScreen(
            onBackClick = { navHostController.popBackStack() }
        )
        summaryScreen(
            onBackClick = { navHostController.popBackStack() },
            onNavigateToDetailScreen = {navHostController.navigateToDetail()}
        )
    }

}

@Composable
fun Navigator(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination,
        builder = builder,
    )
}