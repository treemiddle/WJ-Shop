package com.jay.remote.di

import com.jay.remote.api.ShopApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideShopApi(retrofit: Retrofit): ShopApi {
        return retrofit.create(ShopApi::class.java)
    }
}