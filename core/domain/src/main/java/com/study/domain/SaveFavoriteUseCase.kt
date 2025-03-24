package com.study.domain

import com.study.data.repository.OfflineUserDataRepository
import com.study.model.NewsDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveFavoriteUseCase  @Inject constructor(
    val offlineUserDataRepository: OfflineUserDataRepository
) {
    fun invoke(newsDetail: NewsDetail, isLiked: Boolean): Flow<Any> = flow {
        emit(offlineUserDataRepository.saveFavorites(newsDetail, isLiked))
    }

}