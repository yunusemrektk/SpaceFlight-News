package com.study.domain

import com.study.data.repository.UserDataRepository
import com.study.model.NewsDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    val offlineUserDataRepository: UserDataRepository
) {
    fun invoke(id: NewsDetail): Flow<Any> = flow {
        emit(offlineUserDataRepository.saveFavorites(id, false))
    }
}