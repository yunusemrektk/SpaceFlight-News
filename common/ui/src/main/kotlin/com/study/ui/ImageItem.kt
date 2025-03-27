package com.study.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            modifier = modifier,
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = imageUrl,
            loading = { state ->
                LoadingScreen(Modifier.size(50.dp))
            },
            error = {
                ImageLoadingError("Can not load the image")
            }
        )
    }
}