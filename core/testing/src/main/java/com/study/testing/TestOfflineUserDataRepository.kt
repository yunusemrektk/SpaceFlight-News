package com.study.testing

import com.study.data.repository.UserDataRepository
import com.study.model.NewsDetail
import com.study.model.NewsSummary
import com.study.model.UserData
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

private val emptyUserData = UserData(
    emptyList(),
    emptyMap()
)


class TestOfflineUserDataRepository : UserDataRepository {
    private val _userData = MutableSharedFlow<UserData>(replay = 1, onBufferOverflow = DROP_OLDEST)

    private val currentUserData get() = _userData.replayCache.firstOrNull() ?: emptyUserData

    override val userData: Flow<UserData> = _userData.filterNotNull()

    override fun saveSummaries(newsSummary: List<NewsSummary>) {
        _userData.tryEmit(currentUserData.copy(newsSummary = newsSummary))
    }

    override fun saveDetails(newsDetail: NewsDetail) {
        currentUserData.let { currentData ->
            val updatedDetails = currentData.newsDetail + (newsDetail.id to newsDetail)

            val updatedData = currentData.copy(newsDetail = updatedDetails)
            _userData.tryEmit(
                currentUserData.copy(
                    newsDetail = updatedData.newsDetail,
                    newsSummary = updatedData.newsSummary
                )
            )

        }
    }

    override fun saveFavorites(newDetail: NewsDetail, isLiked: Boolean) {
        currentUserData.let { currentData ->

            var updatedDetail = currentData.newsDetail
            if (updatedDetail.isEmpty()) {
                updatedDetail = currentData.newsDetail + (newDetail.id to newDetail)
            }

            if (isLiked) {
                updatedDetail[newDetail.id]?.isSaved = true
            } else {
                updatedDetail[newDetail.id]?.isSaved = false
            }

            val updatedData = currentData.copy(newsDetail = updatedDetail)


            _userData.tryEmit(
                currentUserData.copy(
                    newsDetail = updatedData.newsDetail,
                    newsSummary = updatedData.newsSummary
                )
            )

        }
    }

    override fun observeAllSummaries(): Flow<List<NewsSummary>> {
        TODO("Not yet implemented")
    }

    override fun observeDetail(id: Int): Flow<NewsDetail> {
        TODO("Not yet implemented")
    }

    override fun observeAllFavorites(): Flow<List<NewsDetail>> = _userData.map { currentData ->
        currentData.newsDetail.values.toList().filter { detail ->
            detail.isSaved
        }
    }


}