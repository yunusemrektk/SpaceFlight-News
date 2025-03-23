package com.study.spaceflightnewsapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.study.favorite.navigation.navigateToFavorite
import com.study.main.navigation.navigateToMain
import com.study.spaceflightnewsapp.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberSpaceFlightAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AppState {
    return remember(
        navController,
        coroutineScope
    ) {
        AppState(navController)
    }
}

class AppState(val navController: NavHostController) {
    var topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
    var currentState : MutableState<TopLevelDestination> = mutableStateOf(TopLevelDestination.HOME)

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        currentState.value = topLevelDestination
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = false
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToMain(topLevelNavOptions)
            TopLevelDestination.FAVORITES -> navController.navigateToFavorite(topLevelNavOptions)
        }
    }
}