package com.study.datastore

import android.content.SharedPreferences
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
){
    private val gson: Gson = Gson()
    private val keyUserData: String = "user_data"

    var initData = UserData(
        newsSummary = emptyList(),
        newsDetail = emptyList(),
        favorites = emptyMap()
    )

    val _userData = MutableStateFlow(initData)

    val userData: Flow<UserData> = _userData.asStateFlow()

    fun saveSummaries(newsSummaries: List<NewsSummary>) {
        _userData.update { currentData ->
            val updatedData = currentData.copy(newsSummary = newsSummaries)
            saveString(userDataToString(updatedData))
            updatedData
        }
    }

    fun saveDetails(newsDetails: List<NewsDetail>) {
        _userData.update { currentData ->
            val updatedData = currentData.copy(newsDetail = newsDetails)
            saveString(userDataToString(updatedData))
            updatedData
        }
    }

    fun saveFavorites(favorites: NewsDetail, isLiked: Boolean) {
        _userData.update { currentData ->
            val updatedFavorites = if (isLiked) {
                currentData.favorites + (favorites to true) // Add
            } else {
                currentData.favorites - favorites // Remove
            }

            val updatedData = currentData.copy(favorites = updatedFavorites)
            saveString(userDataToString(updatedData))
            updatedData
        }
    }

    fun getUserData() {
        _userData.update { currentData ->
            val strUserData = getString()
            if(strUserData.isNotEmpty()) {
                stringToUserData(strUserData)
            } else {
                initData
            }
        }
    }

    fun getFavorites(): Flow<List<NewsDetail>> = _userData.map { currentData ->
            if (currentData.favorites.keys.isNotEmpty()) {
                currentData.favorites.keys.toList()
            } else {
                emptyList()
            }
        }


    fun saveString(value: String) {
        userPreferencesDataSource.edit { putString(keyUserData, value) }
    }

    fun getString(): String {
        return userPreferencesDataSource.getString(keyUserData, "") ?: ""
    }

    private fun userDataToString(userData : UserData): String {
        return gson.toJson(userData)
    }

    private fun stringToUserData(json: String): UserData {
        val type = object : TypeToken<UserData>() {}.type
        return gson.fromJson(json, type)
    }
}