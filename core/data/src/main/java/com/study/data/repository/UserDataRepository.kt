package com.study.data.repository

import com.study.model.NewsDetail
import com.study.model.NewsSummary
import com.study.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>

    fun saveSummaries(newsSummary: List<NewsSummary>)

    fun saveDetails(newsDetail: NewsDetail)

    fun saveFavorites(newDetail: NewsDetail, isLiked: Boolean)

    fun observeAllSummaries(): Flow<List<NewsSummary>>

    fun observeDetail(id: Int): Flow<NewsDetail>

    fun observeAllFavorites(): Flow<List<NewsDetail>>
}