package com.jay.wjshop.di

import android.content.Context
import com.jay.wjshop.ui.home.RecentlyGoodsAdapter
import com.jay.wjshop.ui.home.product.ProductPagerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    @ActivityScoped
    fun provideProductPagerAdapter(
        @ActivityContext context: Context
    ): ProductPagerAdapter {
        return ProductPagerAdapter(context)
    }

    @Provides
    @ActivityScoped
    fun provideRecentlyGoodsAdapter(): RecentlyGoodsAdapter {
        return RecentlyGoodsAdapter()
    }

}