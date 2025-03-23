package com.study.domain

import com.study.data.model.NewsDetail
import com.study.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    val favoriteRepository: FavoriteRepository
){
    fun invoke(): Flow<List<NewsDetail>> = favoriteRepository.getLikedNews()

}