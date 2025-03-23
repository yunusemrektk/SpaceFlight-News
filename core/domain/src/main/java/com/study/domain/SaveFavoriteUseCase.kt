package com.study.domain

import com.study.data.model.NewsDetail
import com.study.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveFavoriteUseCase  @Inject constructor(
    val favoriteRepository: FavoriteRepository
) {
    fun invoke(newsDetail: NewsDetail): Flow<Any> = favoriteRepository.saveLikedNew(newsDetail)

}