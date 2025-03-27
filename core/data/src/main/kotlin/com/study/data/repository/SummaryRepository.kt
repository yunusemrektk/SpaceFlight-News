package com.study.data.repository

import com.study.model.NewsSummary

interface SummaryRepository {
    suspend fun getNewsSummary(): List<NewsSummary>
}

