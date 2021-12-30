package com.jay.data.di

import com.jay.data.WJRepositoryImpl
import com.jay.data.local.WJLocalDataSource
import com.jay.data.remote.WJRemoteDataSource
import com.jay.domain.repository.WJRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWJRepository(
        remoteDataSource: WJRemoteDataSource,
        localDataSource: WJLocalDataSource,
    ): WJRepository {
        return WJRepositoryImpl(remoteDataSource, localDataSource)
    }

}