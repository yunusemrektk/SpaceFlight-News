package com.study.data.repository

import com.study.data.model.NewsSummary

interface SummaryRepository {
    suspend fun getNewsSummary(): List<NewsSummary>
}

