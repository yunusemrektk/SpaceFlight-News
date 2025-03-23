package com.study.data.repository

import com.study.data.model.NewsSummary

interface UserDataRepository {
    suspend fun setNews(newsSummary: List<NewsSummary>)

    suspend fun getSavedNews(): List<NewsSummary>
}