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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.study.ui.SearchBox

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
    modifier: Modifier = Modifier,
    news: List<NewsSummary>,
    errorMessage : String = "",
    onClick: () -> Unit
) {

    Box(modifier = modifier.fillMaxSize()
        .background(Color.Gray)
    ) {
        val searchQuery = remember { mutableStateOf("") }
        Column(modifier = modifier) {
            SearchBox(searchQuery = searchQuery.value ,  onSearchQueryChanged = {searchQuery.value = it}, onSearchTriggered = {})
            SummaryList(news, onClick, searchQuery.value)
        }
    }

    if(errorMessage.isNotEmpty()) {
        ErrorScreen(errorMessage = errorMessage)
    }

}

@Composable
fun SummaryList(
    news: List<NewsSummary>,
    onClick: () -> Unit,
    searchQuery: String
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        news.filter {
            it.title.isNotEmpty() && it.title.contains(searchQuery)
        }.let {
            items(it) { item ->
                NewsItem(
                    title = item.title,
                    summary = item.summary,
                    date = item.date,
                    onClick = onClick
                )
            }
        }
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
            "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
            "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n\n" +
            "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
            "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n\n" +
            "Known as Big George in the ring, the American built one of the most remarkable and enduring careers in the sport, winning Olympic gold in 1968 and claiming the world heavyweight title twice, 21 years apart - the second making him the oldest champion in history aged 45.\n" +
            "He lost his first title to Muhammad Ali in their famous Rumble in the Jungle fight in 1974. But overall, he boasted an astonishing total of 76 winsluding 68 knockouts, almost double that of Ali.\n" +
            "Foreman retired in 1997 but not before he agreed to put his name to a best-selling grill - a decision that went on to bring him fortunes that dwarfed his boxing earnings."

    val box_date = "1 hour ago"

    val delay_title = "After Delays, ESA to Publish Launcher Challenge Call Next Week"
    val delay_summary = "During a press briefing following the 332nd ESA Council meeting, ESA Director General Josef Aschbacher announced that the agency will publish a call for proposals for the European Launcher Challenge in the coming week.\n\n" +
            "Announced in November 2023, the European Launcher Challenge is intended to support the development of sovereign launch capabilities and, ultimately, a successor to Ariane 6. While few specifics have been confirmed, early indications suggest the programme will offer multiple awards of €150 million each.\n\n" +
            "During his annual press briefing in January 2025, ESA Director General Josef Aschbacher stated that the agency would publish a call for proposals, which the agency calls an Invitation to Tender, “around the February timeframe.” However, this tentative timeline wasn’t realized. The Director General has now said that the call will be published next week.\n\n" +
            "“On the European launcher challenge, we had quite an important debate on the future of launchers,” said Aschbacher. “The ITT, the Invitation to Tender, will go out next week and it will, of course, prepare the ground for the smaller launchers, microlaunchers and minilaunchers, to become part of this Challenge. And we do sincerely hope to see some of the first of these new launches being launched very soon.”"
    val delay_date = "12/12/2024"

    val new1 = NewsSummary(box_title, box_summary, box_date)
    val new2 = NewsSummary(delay_title, delay_summary, delay_date)

    listNews.add(new1)
    listNews.add(new1)
    listNews.add(new2)
    return listNews
}


@Preview
@Composable
fun PreviewSummaryScreen() {
    SummaryScreen(news = provideNewsItem()) { }
}