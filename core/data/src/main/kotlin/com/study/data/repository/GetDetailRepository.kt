package com.study.data.repository


import com.study.model.NewsDetail
import com.study.network.model.asExternalModel
import com.study.network.repository.ApiRepository
import javax.inject.Inject

class GetDetailRepository @Inject constructor(
    val apiRepository: ApiRepository,
) : DetailRepository {
    override suspend fun getNewsDetail(id: Int): NewsDetail {
        return apiRepository.getDetail(id).asExternalModel()
    }
}
