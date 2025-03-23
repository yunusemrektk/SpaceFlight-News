package com.study.data.model

data class NewsDetail(
    val id: Int = 0,
    val title: String = "",
    val article: String = "",
    val date: String = "",
    var isSaved: Boolean = false,
    val image: String = ""
)

