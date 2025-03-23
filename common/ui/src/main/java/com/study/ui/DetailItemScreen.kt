package com.study.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun DetailItemScreen(
    title: String,
    article: String,
    imageUrl: String,
    date: String,
    isSaved: Boolean,
    onBackClick: () -> Unit,
    onLikeClick: () -> Unit
){

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)
    ) {
        NewsCard(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .systemBarsPadding()
        ) {
            Column {
                NewsDetailTitleComponent(title = title)
                NewsDetailArticleComponent(modifier = Modifier.weight(1f), summary = article, imageUrl)
                NewsDetailDateComponent(date = date, saved = isSaved, onBackClick = onBackClick,
                    onLikeClicked = { onLikeClick() })
            }
        }
    }
}

@Composable
fun NewsDetailTitleComponent(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(
                text = title,
                style = TextStyle(fontSize = 32.sp, color = Color.White),
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider(color = Color.White, thickness = 2.dp)
        }
    }
}

@Composable
fun NewsDetailArticleComponent(
    modifier: Modifier = Modifier,
    summary: String,
    imageUrl: String
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = summary,
                style = TextStyle(fontSize = 18.sp, color = Color.LightGray),
                fontWeight = FontWeight.Normal
            )
            if(imageUrl.isNotEmpty()) {
                Spacer(modifier = Modifier.height(20.dp))
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "image",
                    modifier = Modifier.height(200.dp)
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        }
    }

}

@Composable
fun NewsDetailDateComponent(
    modifier: Modifier = Modifier,
    date: String,
    saved: Boolean,
    onBackClick:() -> Unit,
    onLikeClicked:() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 15.dp, bottom = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row {

            Icon(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(35.dp)
                    .clickable { onBackClick() },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                tint = Color.LightGray
            )

            Spacer(modifier = Modifier.weight(1f))
            LikeButton(modifier = Modifier.padding(end = 20.dp), saved = saved, onLikeClicked = onLikeClicked)
            Box(
                modifier = modifier
                    .width(110.dp)
                    .height(30.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.Gray)
                    .padding(5.dp)

            ) {
                Text(
                    modifier = modifier.fillMaxSize(),
                    text = date,
                    style = TextStyle(fontSize = 16.sp, color = Color.White),
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}