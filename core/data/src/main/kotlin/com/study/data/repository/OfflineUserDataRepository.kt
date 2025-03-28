package com.study.data.repository

import com.study.datastore.UserPreferencesDataSource
import com.study.model.NewsDetail
import com.study.model.NewsSummary
import com.study.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineUserDataRepository @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource,
) : UserDataRepository {

    override val userData: Flow<UserData> = userPreferencesDataSource.userData

    override fun saveSummaries(newsSummary: List<NewsSummary>) =
        userPreferencesDataSource.saveSummaries(newsSummary)

    override fun saveDetails(newsDetail: NewsDetail) =
        userPreferencesDataSource.saveDetails(newsDetail)

    override fun saveFavorites(newDetail: NewsDetail, isLiked: Boolean) =
        userPreferencesDataSource.saveFavorites(newDetail, isLiked)

    override fun observeAllSummaries(): Flow<List<NewsSummary>> =
        userPreferencesDataSource.getSummaries()

    override fun observeDetail(id: Int): Flow<NewsDetail> = userPreferencesDataSource.getDetail(id)

    override fun observeAllFavorites(): Flow<List<NewsDetail>> =
        userPreferencesDataSource.getFavorites()

}