package com.jay.data

import com.jay.data.mapper.DataShopInfoMapper
import com.jay.data.mapper.DataShopMapper
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
        return remoteDataSource.getShopInfo()
            .map(DataShopInfoMapper::mapToDomain)
    }

    override fun getShop(shopId: Int): Single<List<DomainShop>> {
        return remoteDataSource.getShop(shopId)
            .map(DataShopMapper::mapToDomain)
    }
}