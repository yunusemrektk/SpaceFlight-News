package com.study.data.repository

import com.study.data.convertDateFormat
import com.study.data.model.NewsDetail
import com.study.network.model.Detail
import com.study.network.repository.ApiRepository
import javax.inject.Inject

class GetDetailRepository @Inject constructor(
    val apiRepository: ApiRepository,
): DetailRepository
{
    override suspend fun getNewsDetail(id: Int): NewsDetail {
        return entityDetailToApi(apiRepository.getDetail(id))
    }

    private fun entityDetailToApi(detail: Detail): NewsDetail {
        return NewsDetail(
            id = detail.id,
            title = detail.title,
            article = detail.summary,
            date = convertDateFormat(detail.publishedAt),
            image = detail.imageUrl
        )
    }
}
