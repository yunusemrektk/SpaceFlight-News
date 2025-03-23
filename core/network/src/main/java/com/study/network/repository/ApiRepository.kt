package com.study.network.repository

import com.study.network.ApiService
import com.study.network.model.Detail
import com.study.network.model.Summary
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getSummary(): Summary {
        return apiService.getSummary()
    }

    suspend fun getDetail(id: Int) : Detail {
        return apiService.getDetail(id)
    }
}