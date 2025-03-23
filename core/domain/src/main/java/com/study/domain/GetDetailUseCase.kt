package com.study.domain

import com.study.data.model.NewsDetail
import com.study.data.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetDetailUseCase @Inject constructor(
    val getDetailRepository: DetailRepository
){
    operator fun invoke(id: Int): Flow<NewsDetail> =
        flow {
            emit(getDetailRepository.getNewsDetail(id))
        }
}