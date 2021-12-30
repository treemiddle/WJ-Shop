package com.jay.domain.repository

import com.jay.domain.model.DomainShopInfo
import io.reactivex.Completable
import io.reactivex.Single

interface WJLocalRepository {

    fun getShops(): Single<List<DomainShopInfo>>

    fun updateShop(shop: DomainShopInfo): Completable

    fun deleteAllShops(): Completable

}