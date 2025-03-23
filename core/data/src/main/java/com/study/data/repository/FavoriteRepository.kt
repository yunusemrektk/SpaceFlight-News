package com.study.data.repository

import com.study.data.model.NewsDetail
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun saveLikedNew(detail: NewsDetail): Flow<Any>

    fun getLikedNews(): Flow<List<NewsDetail>>

    fun isItemLiked(id: Int): Flow<Boolean>

    fun removeLikedNews(id: Int): Flow<Any>
}