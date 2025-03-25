package com.study.model

data class UserData(
    val newsSummary: List<NewsSummary>,
    val newsDetail: Map<Int, NewsDetail>,
    val favorites: Map<Int, NewsDetail>
)