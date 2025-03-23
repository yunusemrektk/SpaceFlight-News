package com.study.data.repository

import com.study.data.model.NewsDetail

interface DetailRepository {
    suspend fun getNewsDetail(id: Int): NewsDetail
}