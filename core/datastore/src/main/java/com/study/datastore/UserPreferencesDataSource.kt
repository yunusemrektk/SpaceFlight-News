package com.study.datastore

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val userPreferencesDataSource: SharedPreferences
){
    fun saveString(key: String, value: String) {
        userPreferencesDataSource.edit { putString(key, value) }
    }

    fun getString(key: String, defaultValue: String): String {
        return userPreferencesDataSource.getString(key, defaultValue) ?: defaultValue
    }
}