package com.study.data.repository

import com.study.model.NewsDetail

interface DetailRepository {
    suspend fun getNewsDetail(id: Int): NewsDetail
}