package com.study.spaceflightnewsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.study.spaceflightnewsapp.navigation.AppNavigator

@Composable
fun App(navHostController: NavHostController) {
    AppNavigator(navHostController = navHostController)
}