package com.study.detail

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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.study.ui.ErrorScreen
import com.study.ui.LoadingScreen
import com.study.ui.NewsCard

@Composable
fun DetailRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
) {
    val detailScreenUiState by detailScreenViewModel.detailScreenUiState.collectAsStateWithLifecycle()
    DetailScreen(
        detailScreenUiState,
        onBackClick
    )
}

@Composable
fun DetailScreen(
    detailScreenUIState: DetailScreenUIState,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (detailScreenUIState) {
            is DetailScreenUIState.Loading -> {
                LoadingScreen()
            }

            is DetailScreenUIState.Detail -> {
                DetailItemScreen(detailScreenUIState.detail, detailScreenUIState.errorMessage, onBackClick)
            }
        }

    }
}

@Composable
fun DetailItemScreen(
    detail: NewsDetail,
    errorMessage: String,
    onBackClick: () -> Unit
){
    Box(modifier = Modifier.fillMaxSize()
        .background(Color.Gray)
    ) {
        NewsCard(
            modifier = Modifier.fillMaxSize()
                .navigationBarsPadding()
                .systemBarsPadding()
        ) {
            Column {
                NewsDetailTitleComponent(title = detail.title)
                NewsDetailArticleComponent(modifier = Modifier.weight(1f), summary = detail.article, detail.image)
                NewsDetailDateComponent(date = detail.date, onBackClick = onBackClick)
            }
        }
    }

    if(errorMessage.isNotEmpty()) {
        ErrorScreen(errorMessage = errorMessage)
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
    image: ImageVector
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
            Icon(
                imageVector = image,
                contentDescription = "",
            )
        }
    }

}

@Composable
fun NewsDetailDateComponent(
    modifier: Modifier = Modifier,
    date: String,
    onBackClick:() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 15.dp, bottom = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row {

            Icon(
                modifier = Modifier.padding(start = 20.dp)
                    .size(35.dp)
                    .clickable { onBackClick() },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                tint = Color.LightGray
            )


            Spacer(modifier = Modifier.weight(1f))
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

private fun provideNewsDetail(): NewsDetail {

    val box_title = "Heavyweight boxing legend George Foreman dies aged 76\nasdasda\nasdasd\nasd"
    val box_summary = "Boxing heavyweight legend George Foreman has died aged 76.\n" +
            "\n" +
            "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +

            "Foreman retired in 1997 but not before he agreed to put his name to a best-selling grill - a decision that went on to bring him fortunes that dwarfed his boxing earnings."

    val box_date = "1 hour ago"
    val image = Icons.Default.Check

    return NewsDetail(box_title, box_summary, box_date, image)
}

@Preview
@Composable
fun PreviewDetailScreen() {
    //DetailScreen(provideNewsDetail(), "")
}