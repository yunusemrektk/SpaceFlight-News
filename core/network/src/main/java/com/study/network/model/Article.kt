package com.study.network.model

import com.google.gson.annotations.SerializedName

data class Article(
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