package com.study.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.study.data.convertDateFormat
import com.study.network.model.Summary

data class NewsSummary(
    val id: Int = 0,
    val title: String = "",
    val summary: String = "",
    val date: String = ""
)
val gson = Gson()

fun sharedSummaryToEntity(news : List<NewsSummary>): String {
    return gson.toJson(news)
}

fun sharedSummaryToObject(json: String):List<NewsSummary> {
    val type = object : TypeToken<List<NewsSummary>>() {}.type
    return gson.fromJson(json, type)
}


fun entitySummaryToApi(summary: Summary): List<NewsSummary> {
    val list = ArrayList<NewsSummary>()

    for (item in summary.results) {
        val summary = NewsSummary(
            id = item.id,
            title = item.title,
            summary = item.summary,
            date = convertDateFormat(item.publishedAt)
            )
        list.add(summary)
    }
    return list
}


