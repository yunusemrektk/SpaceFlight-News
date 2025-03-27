package com.study.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShareButton(
    modifier: Modifier = Modifier,
    onShareClicked: () -> Unit
) {

    var color = Color.Gray
    var icon = Icons.Outlined.Share

    IconButton(
        modifier = modifier
            .padding(start = 20.dp)
            .size(30.dp),
        onClick = { onShareClicked() }) {
        Icon(
            imageVector = icon,
            contentDescription = "share_button_desc",
            tint = color,
        )
    }
}