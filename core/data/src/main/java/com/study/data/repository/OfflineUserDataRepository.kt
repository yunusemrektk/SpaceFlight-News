package com.study.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.study.data.model.NewsSummary
import com.study.datastore.UserPreferencesDataSource
import javax.inject.Inject

class OfflineUserDataRepository @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource,
    private val gson:Gson = Gson()
) : UserDataRepository
{
    private val keySummary = "SUMMARIES"

    override suspend fun setNews(newsSummary: List<NewsSummary>) {
        userPreferencesDataSource.saveString(keySummary, sharedSummaryToEntity(newsSummary))
    }

    override suspend fun getSavedNews(): List<NewsSummary> {
        return sharedSummaryToObject(userPreferencesDataSource.getString(keySummary, ""))
    }

    private fun sharedSummaryToEntity(news : List<NewsSummary>): String {
        return gson.toJson(news)
    }

    private fun sharedSummaryToObject(json: String):List<NewsSummary> {
        val type = object : TypeToken<List<NewsSummary>>() {}.type
        return gson.fromJson(json, type)
    }
}