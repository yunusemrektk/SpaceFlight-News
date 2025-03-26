package com.study.domain

import com.study.data.repository.UserDataRepository
import com.study.model.NewsDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    val offlineUserDataRepository: UserDataRepository,
) {
    operator fun invoke(): Flow<List<NewsDetail>> =
        flow {
            offlineUserDataRepository.observeAllFavorites()
                .catch {
                    emit(emptyList())
                }
                .collect { favoriteList ->
                    emit(favoriteList)
                }

        }
}