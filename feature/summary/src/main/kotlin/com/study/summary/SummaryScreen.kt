package com.study.summary

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.study.model.NewsSummary
import com.study.ui.ErrorScreen
import com.study.ui.NewsCard
import com.study.ui.SearchBox

@Composable
fun SummaryRoute(
    onNavigateToDetailScreen: (Int) -> Unit,
    summaryScreenViewModel: SummaryScreenViewModel = hiltViewModel()
) {
    val summaryScreenUiState by summaryScreenViewModel.uiState.collectAsStateWithLifecycle()

    SummaryScreen(
        summaryScreenUiState = summaryScreenUiState,
        onRefresh = summaryScreenViewModel::onRefresh,
        onItemClicked = onNavigateToDetailScreen
    )
}


@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    summaryScreenUiState: SummaryScreenUiState,
    onRefresh: () -> Unit,
    onItemClicked: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        when (summaryScreenUiState) {
            is SummaryScreenUiState.Summary -> {
                SummaryListScreen(
                    news = summaryScreenUiState.newsSummary,
                    isRefreshing = summaryScreenUiState.isRefreshing,
                    errorMessage = summaryScreenUiState.errorMessage,
                    onRefresh = onRefresh,
                    onItemClick = onItemClicked
                )
            }
        }
    }
}

@Composable
fun SummaryListScreen(
    modifier: Modifier = Modifier,
    news: List<NewsSummary>,
    errorMessage: String,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    val searchQuery = remember { mutableStateOf("") }
    Column(modifier = modifier) {
        SearchBox(
            searchQuery = searchQuery.value,
            onSearchQueryChanged = { searchQuery.value = it },
            onSearchTriggered = {})
        SummaryList(
            news = news,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            onItemClick = onItemClick,
            searchQuery = searchQuery.value
        )
    }

    if (errorMessage.isNotEmpty()) {
        ErrorScreen(errorMessage = errorMessage)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryList(
    modifier: Modifier = Modifier,
    news: List<NewsSummary>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onItemClick: (Int) -> Unit,
    searchQuery: String
) {

    val filteredNews = news.filter {
        it.title.isNotEmpty() && it.title.lowercase().contains(searchQuery.lowercase())
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onRefresh() },
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(filteredNews) { item ->
                NewsItem(
                    title = item.title,
                    summary = item.summary,
                    date = item.date,
                    onItemClick = { onItemClick(item.id) }
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
    onItemClick: () -> Unit,
) {
    NewsCard(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(Color.DarkGray)
            .clickable { onItemClick() },
    ) {
        Column {
            NewsItemTitleComponent(title = title)
            NewsItemSummaryComponent(modifier = Modifier.weight(1f), summary)
            NewsBottomItemComponents(date = date)
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
        contentAlignment = Alignment.TopStart
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
fun NewsBottomItemComponents(
    modifier: Modifier = Modifier,
    date: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        NewsItemDateComponent(date = date)
    }
}

@Composable
fun NewsItemDateComponent(
    modifier: Modifier = Modifier,
    date: String
) {
    Box(
        modifier = modifier
            .padding(end = 15.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
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

class SummaryScreenParameterProvider : PreviewParameterProvider<SummaryScreenUiState> {

    private fun provideNew(): List<NewsSummary> {
        val new1 = NewsSummary(0, title = "Title 1", "Article 1", "1 Hour a go")
        val new2 = NewsSummary(0, title = "Title 2", "Article 2", "2 Hour a go")
        return listOf(
            new1, new2
        )
    }

    override val values: Sequence<SummaryScreenUiState> = sequenceOf(
        SummaryScreenUiState.Summary(errorMessage = "Can not update the news", isRefreshing = true),
        SummaryScreenUiState.Summary(newsSummary = provideNew()),
        SummaryScreenUiState.Summary(),
    )
}


@Preview
@Composable
fun PreviewSummaryScreen(
    @PreviewParameter(SummaryScreenParameterProvider::class)
    parameters: SummaryScreenUiState,
) {
    SummaryScreen(summaryScreenUiState = parameters, onRefresh = {}, onItemClicked = {})
}