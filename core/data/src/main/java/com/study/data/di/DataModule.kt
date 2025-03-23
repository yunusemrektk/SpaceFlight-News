package com.study.data.di

import android.content.SharedPreferences
import com.study.data.repository.DetailRepository
import com.study.data.repository.FavoriteNewsRepository
import com.study.data.repository.FavoriteRepository
import com.study.data.repository.GetDetailRepository
import com.study.data.repository.GetSummaryRepository
import com.study.data.repository.OfflineUserDataRepository
import com.study.data.repository.SummaryRepository
import com.study.data.repository.UserDataRepository
import com.study.datastore.UserPreferencesDataSource
import com.study.network.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideOfflineUserRepository(
        sharedPreferences: UserPreferencesDataSource
    ): UserDataRepository {
        return OfflineUserDataRepository(sharedPreferences)
    }


    @Singleton
    @Provides
    fun provideGetSummaryRepository(
        apiRepository: ApiRepository
    ): SummaryRepository {
        return GetSummaryRepository(apiRepository)
    }

    @Singleton
    @Provides
    fun provideGetDetailRepository(
        apiRepository: ApiRepository
    ): DetailRepository {
        return GetDetailRepository(apiRepository)
    }


    @Singleton
    @Provides
    fun provideFavoriteRepository(
        sharedPreferences: UserPreferencesDataSource
    ): FavoriteRepository {
        return FavoriteNewsRepository(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideUserPreferencesDataSource(
        sharedPreferences: SharedPreferences
    ): UserPreferencesDataSource {
        return UserPreferencesDataSource(sharedPreferences)
    }

}