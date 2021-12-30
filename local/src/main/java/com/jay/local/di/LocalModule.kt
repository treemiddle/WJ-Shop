package com.jay.local.di

import android.content.Context
import androidx.room.Room
import com.jay.local.dao.ShopDao
import com.jay.local.database.WJDatabase
import com.jay.local.prefs.PrefsHelper
import com.jay.local.prefs.PrefsHelperImpl
import com.jay.local.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext applicationContext: Context): PrefsHelper {
        return PrefsHelperImpl(applicationContext)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): WJDatabase {
        return Room.databaseBuilder(
            applicationContext,
            WJDatabase::class.java, DATABASE_NAME
        )
            .build()
    }

    @Provides
    fun provideShopDao(database: WJDatabase): ShopDao {
        return database.shopDao()
    }

}