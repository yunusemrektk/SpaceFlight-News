package com.study.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.study.data.model.NewsDetail
import com.study.datastore.UserPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.find

class FavoriteNewsRepository @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource,
):FavoriteRepository {
    private val keyLikes: String = "key_likes"
    private val gson: Gson = Gson()

    override fun saveLikedNew(detail: NewsDetail) : Flow<Any> = flow {
        val list = getLikedNews()
            .map { it.toMutableList() }
            .catch { emit(false) }
            .first()

        var itemIndex = list.indexOfFirst { it.id == detail.id }

        if(itemIndex != -1) {
            list.removeAt(itemIndex)
        } else {
            list.add(detail)
        }

        val result = userPreferencesDataSource.saveString(keyLikes, detailToSharedEntity(list))

        emit(result)
    }

    override fun getLikedNews(): Flow<List<NewsDetail>> = flow {
        val json = userPreferencesDataSource.getString(keyLikes, "")

        emit(sharedDetailToObject(json))
    }

    override fun isItemLiked(id: Int): Flow<Boolean> = flow {
        val isLiked = getLikedNews()
            .map {
                list ->
                list.any {it.id == id}
            }
            .catch { emit(false) }
            .first()

        emit(isLiked)
    }


    override fun removeLikedNews(id: Int) :Flow<Any> = flow{
        val list = getLikedNews()
            .map { it.toMutableList() }
            .catch { emit(false) }
            .first()

        val itemToRemove = list.find { it.id == id }

        val result = if(itemToRemove != null){
            list.remove(itemToRemove)
            userPreferencesDataSource.saveString(keyLikes , detailToSharedEntity(list))
        }else{
            false
        }

        emit(result)
    }

    private fun detailToSharedEntity(news : List<NewsDetail>): String {
        return gson.toJson(news)
    }

    private fun sharedDetailToObject(json: String):List<NewsDetail> {
        if(json.isEmpty()) {
            return emptyList()
        }

        val type = object : TypeToken<List<NewsDetail>>() {}.type
        return gson.fromJson(json, type)
    }
}