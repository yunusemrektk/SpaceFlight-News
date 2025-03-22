package com.study.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.study.ui.ErrorScreen
import com.study.ui.NewsCard

@Composable
fun SummaryRoute(
    onNavigateToDetailScreen: () -> Unit,
    summaryScreenViewModel: SummaryScreenViewModel = hiltViewModel()
) {
    val summaryScreenUiState by summaryScreenViewModel.summaryScreenUiState.collectAsStateWithLifecycle()

    SummaryScreen(
        news = summaryScreenUiState.newsSummary,
        onClick = { onNavigateToDetailScreen() }
    )
}

@Composable
fun SummaryScreen(
    news: List<NewsSummary>,
    errorMessage : String = "",
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()
        .background(Color.Gray)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .navigationBarsPadding()
                .systemBarsPadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(news) {
                NewsItem(
                    title = it.title,
                    summary = it.summary,
                    date = it.date,
                    onClick = onClick
                )
            }

        }
    }

    if(errorMessage.isNotEmpty()) {
        ErrorScreen(errorMessage = errorMessage)
    }
}

@Composable
fun NewsItem(
    title: String = "",
    summary: String = "",
    date: String = "",
    onClick : () -> Unit
) {
    NewsCard(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(Color.DarkGray)
            .clickable { onClick() },
    ) {
        Column {
            NewsItemTitleComponent(title = title)
            NewsItemSummaryComponent(modifier = Modifier.weight(1f), summary)
            NewsItemDateComponent(date = date)
        }
    }
}

@Composable
fun NewsItemTitleComponent(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 24.sp, color = Color.White),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NewsItemSummaryComponent(
    modifier: Modifier,
    summary: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(
                text = summary,
                style = TextStyle(fontSize = 18.sp, color = Color.LightGray),
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun NewsItemDateComponent(
    modifier: Modifier = Modifier,
    date: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 15.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(modifier = modifier
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
                textAlign = TextAlign.Center)
        }
    }
}

private fun provideNewsItem(): MutableList<NewsSummary> {
    val listNews: MutableList<NewsSummary> = emptyList<NewsSummary>().toMutableList()
    val box_title = "Heavyweight boxing legend George Foreman dies aged 76"
    val box_summary = "Boxing heavyweight legend George Foreman has died aged 76.\n" +
            "\n" +
            "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
            "\n" +
            "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n" +
            "\n" +
            "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
            "\n" +
            "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n" +
            "\n" +
            "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
            "\n" +
            "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n" +
            "\n" +

            "Foreman retired in 1997 but not before he agreed to put his name to a best-selling grill - a decision that went on to bring him fortunes that dwarfed his boxing earnings."

    val box_date = "1 hour ago"
    val new1 = NewsSummary(box_title, box_summary, box_date)

    listNews.add(new1)
    listNews.add(new1)
    listNews.add(new1)
    return listNews
}


@Preview
@Composable
fun PreviewSummaryScreen() {
    SummaryScreen(provideNewsItem()) { }
}