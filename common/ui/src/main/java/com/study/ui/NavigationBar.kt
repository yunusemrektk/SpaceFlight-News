package com.study.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SpaceFlightNavigationBar(
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = Modifier,
        contentColor = NavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}


@Composable
fun RowScope.NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NavigationDefaults.navigationContentColor(),
            selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NavigationDefaults.navigationContentColor(),
            indicatorColor = NavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

object NavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}


@Preview
@Composable
fun PreviewNavigationBar() {
    val items = listOf("Home", "Favorite")
    val icons = listOf(
        Icons.Rounded.Home,
        Icons.Rounded.Home,
    )
    val selectedIcons = listOf(
        Icons.Rounded.Home,
        Icons.Rounded.Home,
    )

    SpaceFlightNavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = items[index],
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = selectedIcons[index],
                        contentDescription = items[index],
                    )
                },
                label = { Text(items[index]) },
                selected = index == 0,
                onClick = { },
            )
        }
    }
}
