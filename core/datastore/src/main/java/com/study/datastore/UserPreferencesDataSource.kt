package com.study.datastore

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.study.model.NewsDetail
import com.study.model.NewsSummary
import com.study.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val userPreferencesDataSource: SharedPreferences,
) {
    private val gson: Gson = Gson()
    private val keyUserData: String = "user_data"


    val _userData = MutableStateFlow(
        getUserData()
    )
    val userData: Flow<UserData> = _userData.asStateFlow()

    fun getUserData(): UserData {
        var strUserData = ""
        try {
            strUserData = getString()
        } catch (_: Exception) {
            Log.e("UserPreferencesDataSource", "Error while getting the data from shared pref")
        }

        return if (strUserData.isNotEmpty()) {
            stringToUserData(strUserData)
        } else {
            UserData(
                newsSummary = emptyList(),
                newsDetail = emptyMap()
            )
        }
    }

    fun saveSummaries(newsSummaries: List<NewsSummary>) {
        _userData.update { currentData ->
            val updatedData = currentData.copy(newsSummary = newsSummaries)
            saveString(userDataToString(updatedData))
            updatedData
        }
    }

    fun saveDetails(newsDetails: NewsDetail) {
        _userData.update { currentData ->
            val updatedDetails = currentData.newsDetail + (newsDetails.id to newsDetails)

            val updatedData = currentData.copy(newsDetail = updatedDetails)
            saveString(userDataToString(updatedData))
            updatedData
        }
    }

    fun saveFavorites(newDetail: NewsDetail, isLiked: Boolean) {
        _userData.update { currentData ->
            var updatedDetail = currentData.newsDetail
            if(updatedDetail.isEmpty()) {
                updatedDetail = currentData.newsDetail + (newDetail.id to newDetail)
            }

            if (isLiked) {
                updatedDetail[newDetail.id]?.isSaved = true
            } else {
                updatedDetail[newDetail.id]?.isSaved = false
            }

            val updatedData = currentData.copy(newsDetail = updatedDetail)
            saveString(userDataToString(updatedData))
            updatedData
        }
    }


    fun getSummaries(): Flow<List<NewsSummary>> = _userData.map { currentData ->
        currentData.newsSummary
    }

    fun getDetail(id: Int): Flow<NewsDetail> = _userData.map { currentData ->
        currentData.newsDetail[id] as NewsDetail
    }


    fun getFavorites(): Flow<List<NewsDetail>> = _userData.map { currentData ->
        currentData.newsDetail.values.toList().filter { detail ->
            detail.isSaved
        }

    }

    fun saveString(value: String) {
        userPreferencesDataSource.edit { putString(keyUserData, value) }
    }

    fun getString(): String {
        return userPreferencesDataSource.getString(keyUserData, "") ?: ""
    }

    private fun userDataToString(userData: UserData): String {
        return gson.toJson(userData)
    }

    private fun stringToUserData(json: String): UserData {
        val type = object : TypeToken<UserData>() {}.type
        return gson.fromJson(json, type)
    }
}