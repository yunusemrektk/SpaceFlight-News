package com.study.data.repository

import com.study.model.NewsDetail
import com.study.model.NewsSummary
import com.study.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>

    fun saveSummaries(newsSummary: List<NewsSummary>)

    fun saveDetails(newsSummary: List<NewsDetail>)

    fun saveFavorites(newsSummary: NewsDetail, isLiked: Boolean)

    fun observeAllFavorites() : Flow<List<NewsDetail>>
}