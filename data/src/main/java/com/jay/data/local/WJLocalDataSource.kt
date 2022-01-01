package com.jay.data.local

import com.jay.data.model.DataGoods
import com.jay.data.model.DataShopAndGoods
import com.jay.data.model.DataShopInfo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface WJLocalDataSource {

    fun insertAllShops(shops: List<DataShopInfo>): Completable

    fun getShops(): Single<List<DataShopInfo>>

    fun updateShop(shop: DataShopInfo): Completable

    fun deleteAll(): Completable

    fun insertGoods(goods: DataGoods): Completable

    fun clearGoods(): Completable

    fun getGoodsByShopId(shopId: Int): Flowable<DataShopAndGoods>

}