package com.jay.domain.usecase

import com.jay.domain.model.DomainShopInfo
import com.jay.domain.repository.WJRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalUseCase @Inject constructor(private val repository: WJRepository) {

    fun getShops(): Single<List<DomainShopInfo>> {
        return repository.getShops()
    }

    fun updateShop(shop: DomainShopInfo): Completable {
        return repository.updateShop(shop)
    }

    fun deleteAllShops(): Completable {
        return repository.deleteAllShops()
    }

}