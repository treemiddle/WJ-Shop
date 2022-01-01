package com.jay.local.di

import com.jay.data.local.WJLocalDataSource
import com.jay.local.WJLocalDataSourceImpl
import com.jay.local.dao.ShopDao
import com.jay.local.dao.TestDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    //todo TestDao
    @Provides
    @Singleton
    fun provideLocalDataSource(
        shopDao: ShopDao,
        testDao: TestDao
    ): WJLocalDataSource {
        return WJLocalDataSourceImpl(shopDao, testDao)
    }
    
}