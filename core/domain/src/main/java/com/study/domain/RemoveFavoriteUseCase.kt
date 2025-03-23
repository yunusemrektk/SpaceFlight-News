package com.study.domain

import com.study.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    val favoriteRepository: FavoriteRepository
) {
    fun invoke(id: Int): Flow<Any> = favoriteRepository.removeLikedNews(id)

}