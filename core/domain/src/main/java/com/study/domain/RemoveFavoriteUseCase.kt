package com.study.domain

import com.study.data.repository.OfflineUserDataRepository
import com.study.model.NewsDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    val offlineUserDataRepository: OfflineUserDataRepository
) {
    fun invoke(id: NewsDetail): Flow<Any> = flow {
        offlineUserDataRepository.saveFavorites(id, false)

    }


}