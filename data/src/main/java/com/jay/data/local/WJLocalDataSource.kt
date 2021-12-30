package com.jay.data.local

import com.jay.data.model.DataShopInfo
import io.reactivex.Completable
import io.reactivex.Single

interface WJLocalDataSource {

    fun insertAllShops(shops: List<DataShopInfo>): Completable

    fun getShops(): Single<List<DataShopInfo>>

    fun updateShop(shop: DataShopInfo): Completable

    fun deleteAll(): Completable
}