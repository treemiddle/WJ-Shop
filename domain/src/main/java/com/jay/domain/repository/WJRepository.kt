package com.jay.domain.repository

import com.jay.domain.model.DomainShop
import com.jay.domain.model.DomainShopInfo
import io.reactivex.Completable
import io.reactivex.Single

interface WJRepository {

    fun getShops(): Single<List<DomainShopInfo>>

    fun updateShop(shop: DomainShopInfo): Completable

    fun deleteAllShops(): Completable

    fun getGoods(shopId: Int): Single<List<DomainShop>>

}