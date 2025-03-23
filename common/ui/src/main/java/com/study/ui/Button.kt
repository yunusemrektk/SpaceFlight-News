package com.study.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Button (
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    text: String = ""
) {
    androidx.compose.material3.Button(
        onClick = { onClick() },
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Gray)
            .padding(start = 15.dp, end = 15.dp),
        enabled = true,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        content = {
            if(text.isNotEmpty()) {
                Text(
                    text = text,
                    style = TextStyle(fontSize = 16.sp, color = Color.White),
                    fontWeight = FontWeight.Normal
                )
            }
        },
    )
}

@Preview
@Composable
fun PreviewButton() {
    Button(onClick = {}, text = "Try Again")
}