package com.study.domain

import com.study.data.model.NewsSummary
import com.study.data.repository.SummaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSummaryUseCase @Inject constructor(
    val getSummaryRepository: SummaryRepository
){
    operator fun invoke(): Flow<List<NewsSummary>> =
        flow {
            emit(getSummaryRepository.getNewsSummary())
        }
}