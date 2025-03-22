package com.study.detail.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.study.detail.DetailRoute

const val DETAIL_ROUTE = "detail_screen_route"

fun NavController.navigateToDetail(){
    this.navigate(route = DETAIL_ROUTE)
}

fun NavGraphBuilder.detailScreen(
    onBackClick: () -> Unit
) {
    composable(route = DETAIL_ROUTE) {
        BackHandler (true) {}
        DetailRoute(
            onBackClick = onBackClick
        )

    }
}