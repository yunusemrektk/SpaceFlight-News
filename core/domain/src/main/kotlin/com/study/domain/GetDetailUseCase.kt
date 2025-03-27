package com.study.domain

import com.study.data.repository.DetailRepository
import com.study.model.NewsDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetDetailUseCase @Inject constructor(
    val getDetailRepository: DetailRepository
) {
    operator fun invoke(id: Int): Flow<NewsDetail> =
        flow {
            emit(getDetailRepository.getNewsDetail(id))
        }
}