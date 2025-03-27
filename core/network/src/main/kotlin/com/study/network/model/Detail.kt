package com.study.network.model

import com.google.gson.annotations.SerializedName
import com.study.model.NewsDetail
import com.study.network.model.sub.Author
import com.study.network.model.sub.Event
import com.study.network.model.sub.Launch
import com.study.network.util.convertDateFormat

data class Detail(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val url: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("news_site")
    val newsSite: String,
    val summary: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val featured: Boolean,
    val launches: List<Launch>,
    val events: List<Event>
)

fun Detail.asExternalModel() = NewsDetail(
    id = id,
    title = title,
    article = summary,
    date = convertDateFormat(publishedAt),
    image = imageUrl
)