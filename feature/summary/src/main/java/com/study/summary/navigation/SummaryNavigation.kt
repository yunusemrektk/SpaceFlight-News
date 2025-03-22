package com.study.summary.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.study.summary.SummaryRoute


const val SUMMARY_ROUTE = "summary_screen_route"

fun NavController.navigateToSummary(){
    this.navigate(route = SUMMARY_ROUTE)
}

fun NavGraphBuilder.summaryScreen(
    onBackClick: () -> Unit,
    onNavigateToDetailScreen: () -> Unit
) {
    composable(route = SUMMARY_ROUTE) {
        BackHandler (true) { onBackClick() }
        SummaryRoute(
            onNavigateToDetailScreen

        )

    }
}