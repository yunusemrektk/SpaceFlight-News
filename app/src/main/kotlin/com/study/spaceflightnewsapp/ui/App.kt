package com.study.spaceflightnewsapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.study.spaceflightnewsapp.navigation.SpaceNavHost
import com.study.ui.NavigationBarItem
import com.study.ui.SpaceFlightNavigationBar

@Composable
fun App(appState: AppState, navHostController: NavHostController) {
    Scaffold(
        bottomBar = {
            SpaceFlightNavigationBar {
                val currentState = remember { mutableStateOf(appState.currentState)  }
                appState.topLevelDestinations.forEach { destination ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = destination.unselectedIcon,
                                contentDescription = destination.title,
                            )
                        },
                        selectedIcon = {
                            Icon(
                                imageVector = destination.selectedIcon,
                                contentDescription = destination.title,
                            )
                        },
                        label = { Text(destination.title) },
                        selected = currentState.value.value == destination,
                        onClick = { appState.navigateToTopLevelDestination(destination) },
                    )
                }
            }
        }
    ) { padding ->
        SpaceNavHost(modifier = Modifier.padding(padding), navHostController = navHostController)
    }

}