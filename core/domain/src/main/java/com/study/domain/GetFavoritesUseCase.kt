package com.study.domain

import com.study.data.repository.OfflineUserDataRepository
import com.study.model.NewsDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    val offlineUserDataRepository: OfflineUserDataRepository
){
    fun invoke(): Flow<List<NewsDetail>> = offlineUserDataRepository.observeAllFavorites()
}