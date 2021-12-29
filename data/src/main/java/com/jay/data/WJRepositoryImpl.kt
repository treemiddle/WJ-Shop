package com.jay.data

import com.jay.data.mapper.shopInfoMapToDomain
import com.jay.data.mapper.shopMapToDomain
import com.jay.data.remote.WJRemoteDataSource
import com.jay.domain.model.DomainShop
import com.jay.domain.model.DomainShopInfo
import com.jay.domain.repository.WJRepository
import io.reactivex.Single
import javax.inject.Inject

class WJRepositoryImpl @Inject constructor(
    private val remoteDataSource: WJRemoteDataSource
) : WJRepository {

    override fun getShopInfo(): Single<List<DomainShopInfo>> {
        return remoteDataSource.getShopInfo().map { it.shopInfoMapToDomain() }
    }

    override fun getShop(shopId: Int): Single<List<DomainShop>> {
        return remoteDataSource.getShop(shopId).map { it.shopMapToDomain() }
    }
}