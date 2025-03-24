package com.study.data.repository

import com.study.model.NewsSummary
import com.study.network.model.asExternalModel
import com.study.network.repository.ApiRepository
import javax.inject.Inject

class GetSummaryRepository @Inject constructor(
    val apiRepository: ApiRepository,
): SummaryRepository
{

    override suspend fun getNewsSummary(): List<NewsSummary> {
       return apiRepository.getSummary().asExternalModel()
    }
}
