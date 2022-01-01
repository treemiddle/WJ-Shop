package com.jay.domain.repository

import com.jay.domain.model.DomainGoods
import com.jay.domain.model.DomainShop
import com.jay.domain.model.DomainShopAndGoods
import com.jay.domain.model.DomainShopInfo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface WJRepository {

    fun getShops(): Single<List<DomainShopInfo>>

    fun updateShop(shop: DomainShopInfo): Completable

    fun deleteAllShops(): Completable

    // remote
    fun getGoods(shopId: Int): Single<List<DomainShop>>

    fun insertGoods(goods: DomainGoods): Completable

    fun clearGoods(): Completable

    fun getGoodsByShopId(shopdId: Int): Flowable<DomainShopAndGoods>

}