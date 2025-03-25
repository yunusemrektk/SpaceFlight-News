package com.study.domain

import android.util.Log
import com.study.data.repository.OfflineUserDataRepository
import com.study.data.repository.SummaryRepository
import com.study.model.NewsSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSummaryUseCase @Inject constructor(
    val getSummaryRepository: SummaryRepository,
    val offlineUserDataRepository: OfflineUserDataRepository,
    val getDetailUseCase: GetDetailUseCase
){
    operator fun invoke(): Flow<List<NewsSummary>> =
        flow {
            val newsSummary = getSummaryRepository.getNewsSummary()
            emit(newsSummary)

            offlineUserDataRepository.saveSummaries(newsSummary)

            newsSummary.forEachIndexed { index, item ->
                getDetailUseCase.invoke(item.id)
                    .catch {
                        //Error while getting the details
                        Log.e("GetSummaryUseCase","error while getting the details: $item.id")
                    }
                    .collect { detail ->
                        offlineUserDataRepository.saveDetails(detail)
                    }
            }
        }.catch {
            Log.e("GetSummaryUseCase","error while getting summaries")
            //Error while getting the summaries so go to offline
            emit(emptyList())
        }
}