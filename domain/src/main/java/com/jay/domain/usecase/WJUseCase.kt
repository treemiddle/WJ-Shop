package com.jay.domain.usecase

import com.jay.domain.model.DomainShop
import com.jay.domain.model.DomainShopInfo
import com.jay.domain.repository.WJRepository
import io.reactivex.Single
import javax.inject.Inject

class WJUseCase @Inject constructor(private val repository: WJRepository) {

    fun getShops(): Single<List<DomainShopInfo>> {
        return repository.getShops()
    }

    fun getGoods(shopId: Int): Single<List<DomainShop>> {
        return repository.getGoods(shopId)
    }

}