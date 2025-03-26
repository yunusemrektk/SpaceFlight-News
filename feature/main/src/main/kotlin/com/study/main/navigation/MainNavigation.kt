package com.study.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.study.main.MainRoute

const val MAIN_ROUTE = "main_screen_route"

fun NavController.navigateToMain(navOptions: NavOptions) {
    this.navigate(route = MAIN_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.mainScreen(
    onNavigateToSummaryScreen: () -> Unit,
) {
    composable(route = MAIN_ROUTE) {
        MainRoute(
            onNavigateToSummaryScreen = onNavigateToSummaryScreen
        )
    }
}