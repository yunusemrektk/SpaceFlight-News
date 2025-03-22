package com.study.spaceflightnewsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector


enum class TopLevelDestination(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
{
    HOME(
        title = "Home",
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    FAVORITES(
        title = "Favorites",
        selectedIcon = Icons.Rounded.Favorite,
        unselectedIcon = Icons.Outlined.Favorite
    )
}
