package com.jay.domain.di

import com.jay.domain.repository.WJRepository
import com.jay.domain.usecase.WJUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideWJUseCase(wjRepository: WJRepository): WJUseCase {
        return WJUseCase(wjRepository)
    }
}