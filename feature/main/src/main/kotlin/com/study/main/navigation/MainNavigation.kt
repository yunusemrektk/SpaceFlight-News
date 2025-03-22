package com.study.main.navigation

import androidx.activity.compose.BackHandler
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
    onBackClick: () -> Unit,
    onNavigateToSummaryScreen: () -> Unit,
) {
    composable(route = MAIN_ROUTE) {
        BackHandler (true) { onBackClick() }
        MainRoute(
            onNavigateToSummaryScreen = onNavigateToSummaryScreen
        )
    }
}