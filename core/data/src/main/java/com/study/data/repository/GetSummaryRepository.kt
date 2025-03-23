package com.study.data.repository

import com.study.data.model.NewsSummary
import com.study.data.model.entitySummaryToApi
import com.study.network.repository.ApiRepository
import javax.inject.Inject

class GetSummaryRepository @Inject constructor(
    val apiRepository: ApiRepository,
): SummaryRepository
{

    override suspend fun getNewsSummary(): List<NewsSummary> {
       return entitySummaryToApi(apiRepository.getSummary())
    }
}
