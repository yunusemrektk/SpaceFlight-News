package com.study.domain

import android.util.Log
import com.study.data.repository.OfflineUserDataRepository
import com.study.model.NewsSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOfflineUserDataUseCase @Inject constructor(
    val offlineUserDataRepository: OfflineUserDataRepository,
) {
    operator fun invoke(): Flow<List<NewsSummary>> =
        flow {
            offlineUserDataRepository.observeAllSummaries()
                .catch {
                    emit(emptyList())
                    Log.e("GetSummaryUseCase", "error while getting offline summaries")
                }
                .collect { summaries ->
                    emit(summaries)
                }
        }
}