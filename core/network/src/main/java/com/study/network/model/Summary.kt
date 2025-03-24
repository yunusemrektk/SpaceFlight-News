package com.study.network.model

import com.study.model.NewsSummary
import com.study.network.model.sub.Article
import com.study.network.util.convertDateFormat

data class Summary(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Article>
)

fun Summary.asExternalModel(): ArrayList<NewsSummary> {
    val list = ArrayList<NewsSummary>()

    for (item in results) {
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





