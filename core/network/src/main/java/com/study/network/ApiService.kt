package com.study.network

import com.study.network.model.Detail
import com.study.network.model.Summary
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(value = "articles")
    suspend fun getSummary(): Summary

    @GET(value = "articles/{id}")
    suspend fun getDetail(
        @Path("id") id: Int?,
    ): Detail
}
