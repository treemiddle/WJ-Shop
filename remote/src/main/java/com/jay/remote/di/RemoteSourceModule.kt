package com.jay.remote.di

import com.jay.data.remote.WJRemoteDataSource
import com.jay.remote.WJRemoteDataSourceImpl
import com.jay.remote.api.ShopApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {

    @Provides
    @Singleton
    fun provideWJRemoteDataSource(shopApi: ShopApi): WJRemoteDataSource {
        return WJRemoteDataSourceImpl(shopApi)
    }
}