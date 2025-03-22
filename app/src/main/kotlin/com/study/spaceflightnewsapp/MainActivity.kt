package com.study.spaceflightnewsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.study.spaceflightnewsapp.ui.App
import com.study.spaceflightnewsapp.ui.rememberSpaceFlightAppState
import com.study.spaceflightnewsapp.ui.theme.SpaceflightNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            val appState = rememberSpaceFlightAppState(navController = navHostController)

            SpaceflightNewsAppTheme {
                App(appState, navHostController)
            }
        }
    }
}
