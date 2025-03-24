package com.study.domain

import com.study.data.repository.OfflineUserDataRepository
import com.study.data.repository.SummaryRepository
import com.study.model.NewsSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSummaryUseCase @Inject constructor(
    val getSummaryRepository: SummaryRepository,
    val offlineUserDataRepository: OfflineUserDataRepository
){
    operator fun invoke(): Flow<List<NewsSummary>> =
        flow {
            val newsSummary = getSummaryRepository.getNewsSummary()
            offlineUserDataRepository.saveSummaries(newsSummary)
            emit(newsSummary)
        }.catch {
            //Error while getting the summaries so go to offline
        }
}