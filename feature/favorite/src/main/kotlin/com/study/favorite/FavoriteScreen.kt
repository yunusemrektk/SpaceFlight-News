package com.study.favorite

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.study.model.NewsDetail
import com.study.ui.DetailItemScreen
import com.study.ui.LoadingScreen
import com.study.ui.NewsCard
import com.study.ui.SearchBox

@Composable
fun FavoriteRoute(
    favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel()
) {

    val favoriteScreenUiState by favoriteScreenViewModel.favoriteScreenUiState.collectAsStateWithLifecycle()
    val favoriteListUiState by favoriteScreenViewModel.favoritesUiState.collectAsStateWithLifecycle()

    FavoriteScreen(
        favoriteScreenUiState = favoriteScreenUiState,
        favoriteListUiState = favoriteListUiState,
        onBackClick = favoriteScreenViewModel::onBackClicked,
        onDetailClick = favoriteScreenViewModel::onDetailClicked,
        onLikeClick =  favoriteScreenViewModel::onLikeClicked
    )
}

@Composable
fun FavoriteScreen(
    favoriteScreenUiState: FavoriteScreenUiState,
    favoriteListUiState: FavoriteListsUiState,
    onBackClick: () -> Unit,
    onDetailClick: (NewsDetail) -> Unit,
    onLikeClick: (NewsDetail) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (favoriteScreenUiState) {
            is FavoriteScreenUiState.Loading -> {
                LoadingScreen()
            }

            is FavoriteScreenUiState.Detail -> {
                FavoriteDetail(
                    newsDetail = favoriteScreenUiState.detail,
                    onBackClick = onBackClick,
                    onLikeClick = onLikeClick
                )
            }

            is FavoriteScreenUiState.ListView -> {
                FavoriteListScreen(
                    favoriteListUiState = favoriteListUiState,
                    onDetailClick = onDetailClick
                )
            }
        }
    }

}

@Composable
fun FavoriteListScreen(
    favoriteListUiState: FavoriteListsUiState,
    onDetailClick: (NewsDetail) -> Unit
) {

    when(favoriteListUiState) {
        is FavoriteListsUiState.Loading -> {

        }

        is FavoriteListsUiState.Success -> {
            Log.e("MYTEST","EMIT SUCCCESS: ${favoriteListUiState.favorites.size}")
            FavoritesGrid(
                news = favoriteListUiState.favorites,
                onDetailClick = onDetailClick
            )
        }
    }
}

@Composable
fun FavoritesGrid(
    modifier: Modifier = Modifier,
    news: List<NewsDetail>,
    onDetailClick: (NewsDetail) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        val searchQuery = remember { mutableStateOf("") }
        Column(modifier = modifier) {
            SearchBox(
                searchQuery = searchQuery.value,
                onSearchQueryChanged = { searchQuery.value = it },
                onSearchTriggered = {})
            FavoriteList(
                news = news,
                onItemClick = onDetailClick,
                searchQuery = searchQuery.value)
        }
    }

}

@Composable
fun FavoriteDetail(
    newsDetail: NewsDetail,
    onBackClick: () -> Unit,
    onLikeClick: (NewsDetail) -> Unit
) {
    DetailItemScreen(
        title = newsDetail.title,
        article =  newsDetail.article,
        imageUrl = newsDetail.image,
        date = newsDetail.date,
        isSaved = true,
        onBackClick = onBackClick,
        onLikeClick = { onLikeClick(newsDetail) }
    )
}

@Composable
fun FavoriteList(
    news: List<NewsDetail>,
    onItemClick: (NewsDetail) -> Unit,
    searchQuery: String
) {
    if(news.isEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            text = "No favorite article has found",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 16.sp, color = Color.LightGray)
        )
        return
    }

    val filteredFavorites = news.filter { detail ->
        detail.title.isNotEmpty() && detail.title.lowercase().contains(searchQuery.lowercase())
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(filteredFavorites) { item ->
            FavoriteItem(
                title = item.title,
                onItemClick = { onItemClick(item) },
            )

        }
    }
}

@Composable
fun FavoriteItem(
    modifier: Modifier = Modifier,
    title: String = "",
    onItemClick: () -> Unit
) {
    NewsCard(
        modifier = modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(Color.DarkGray)
            .clickable { onItemClick() },
    ) {
        Column {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = title,
                    style = TextStyle(fontSize = 18.sp, color = Color.White),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}