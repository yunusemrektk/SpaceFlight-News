package com.study.main.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.study.main.MainScreen

const val MAIN_ROUTE = "main_screen_route"


fun NavController.navigateToMain(){
    this.navigate(route = MAIN_ROUTE)
}

fun NavGraphBuilder.mainScreen(
    onBackClick: () -> Unit,
    onDetailsClick : () -> Unit
) {
    composable(route = MAIN_ROUTE) {
        BackHandler (true) {}
        MainScreen(
            onBackClick = onBackClick,
            onDetailClick = onDetailsClick
        )

    }
}