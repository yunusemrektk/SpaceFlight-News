package com.study.spaceflightnewsapp.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.study.main.navigation.*

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
            onDetailsClick = {}
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