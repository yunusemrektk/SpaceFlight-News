package com.study.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.study.ui.DetailParameterProvider.DetailItemParameter

@Composable
fun DetailItemScreen(
    title: String,
    article: String,
    imageUrl: String,
    date: String,
    isSaved: Boolean,
    onBackClick: () -> Unit,
    onLikeClick: (Boolean) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        NewsCard(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .systemBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                NewsDetailTopComponent(
                    date = date,
                    saved = isSaved,
                    onBackClick = onBackClick,
                    onShareClick = { onShareClicked(context, title, article) },
                    onLikeClicked = onLikeClick
                )
                ImageItem(imageUrl = imageUrl)
                NewsDetailTitleComponent(title = title)
                NewsDetailArticleComponent(
                    summary = article,
                    imageUrl = imageUrl
                )
            }
        }
    }
}

fun onShareClicked(context: Context, title: String, article: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, article)
        putExtra(Intent.EXTRA_TITLE, title)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}

@Composable
fun ImageLoadingError(textError: String = "") {
    Column(
        modifier = Modifier
            .size(50.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(60.dp),
            imageVector = Icons.Default.Warning,
            contentDescription = "",
            tint = Color.Yellow
        )

        Text(
            text = textError,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun NewsDetailTitleComponent(
    title: String
) {
    Box(
        modifier = Modifier
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
    summary: String,
    imageUrl: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = summary,
            style = TextStyle(fontSize = 18.sp, color = Color.LightGray),
            fontWeight = FontWeight.Normal,

            )
        if (imageUrl.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun NewsDetailTopComponent(
    modifier: Modifier = Modifier,
    date: String,
    saved: Boolean,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onLikeClicked: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 15.dp, top = 10.dp),
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
            ShareButton(modifier = Modifier.padding(end = 10.dp), onShareClicked = onShareClick)
            LikeButton(
                modifier = Modifier.padding(end = 20.dp),
                saved = saved,
                onLikeClicked = onLikeClicked
            )
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


class DetailParameterProvider : PreviewParameterProvider<DetailItemParameter> {

    data class DetailItemParameter(
        val id: Int = 0,
        val title: String,
        val article: String,
        val image: String,
        val date: String,
        val isSaved: Boolean = false
    )

    val new1 = DetailItemParameter(
        id = 0,
        title = "Not Just for Engineers: Broadening the Space Pipeline",
        article = "In this week's episode of Space Minds, Sara Alvarado, Executive Director for the Students for the Exploration and Development of Space, known as SEDS, sits down with host David Ariosto.\\nThe post Not Just for Engineers: Broadening the Space Pipeline appeared first on SpaceNews.",
        image = "https://i0.wp.com/spacenews.com/wp-content/uploads/2025/03/2000x1500-Sara-Alvarado.png?fit=1024%2C768&quality=80&ssl=1",
        date = "today",
        isSaved = true
    )


    override val values: Sequence<DetailItemParameter> = sequenceOf(
        new1,
        new1
    )

}

@Preview
@Preview(
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun PreviewFavoriteScreen(
    @PreviewParameter(DetailParameterProvider::class) parameter: DetailItemParameter
) {
    DetailItemScreen(
        parameter.title,
        parameter.article,
        parameter.image,
        parameter.date,
        true,
        onBackClick = {},
        onLikeClick = {},
    )
}
