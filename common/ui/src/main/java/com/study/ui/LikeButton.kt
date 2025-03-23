package com.study.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable

fun LikeButton(
    modifier: Modifier = Modifier,
    saved: Boolean,
    onLikeClicked: () -> Unit
) {
    val isLiked = remember { mutableStateOf(saved) }

    var color = Color.Gray
    var icon = Icons.Outlined.FavoriteBorder

    if (isLiked.value) {
        color = Color.Red
        Icons.Rounded.Favorite
    }

    IconButton(
        modifier = modifier
            .padding(start = 20.dp)
            .size(30.dp),
        onClick = {
            isLiked.value = !isLiked.value
            onLikeClicked()
        }) {
        Icon(
            imageVector = icon,
            contentDescription = "navigationIconContentDescription",
            tint = color,
        )
    }
}