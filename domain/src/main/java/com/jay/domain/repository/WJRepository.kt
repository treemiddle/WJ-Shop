package com.jay.domain.repository

import com.jay.domain.model.DomainShop
import com.jay.domain.model.DomainShopInfo
import io.reactivex.Single

interface WJRepository {

    fun getShopInfo(): Single<List<DomainShopInfo>>

    fun getShop(shopId: Int): Single<List<DomainShop>>
}