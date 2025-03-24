package com.study.model

data class UserData(
    val newsSummary: List<NewsSummary>,
    val newsDetail: List<NewsDetail>,
    val favorites: Map<NewsDetail, Boolean>
)