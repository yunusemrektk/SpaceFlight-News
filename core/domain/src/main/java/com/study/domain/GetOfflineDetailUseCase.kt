package com.study.domain

import android.util.Log
import com.study.data.repository.UserDataRepository
import com.study.model.NewsDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOfflineDetailUseCase @Inject constructor(
    val offlineUserDataRepository: UserDataRepository,
) {
    operator fun invoke(itemId: Int): Flow<NewsDetail?> =
        flow {
            offlineUserDataRepository.observeDetail(itemId)
                .catch {
                    Log.e("GetOfflineDetailUseCase", "Get Detail Success: $itemId")
                    emit(null)
                }
                .collect { item ->
                    Log.e("GetOfflineDetailUseCase", "Get Detail Error: $itemId")
                    emit(item)
                }
        }
}