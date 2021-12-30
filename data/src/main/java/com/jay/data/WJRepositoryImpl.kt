package com.jay.data

import com.jay.data.local.WJLocalDataSource
import com.jay.data.mapper.DataShopInfoMapper
import com.jay.data.mapper.DataShopInfoMapper.mapToLocal
import com.jay.data.mapper.DataShopMapper
import com.jay.data.remote.WJRemoteDataSource
import com.jay.domain.model.DomainShop
import com.jay.domain.model.DomainShopInfo
import com.jay.domain.repository.WJRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class WJRepositoryImpl @Inject constructor(
    private val remoteDataSource: WJRemoteDataSource,
    private val localDataSource: WJLocalDataSource,
) : WJRepository {

    override fun getShops(): Single<List<DomainShopInfo>> {
        return localDataSource.getShops()
            .flatMap { cachedShops ->
                if (cachedShops.isEmpty()) {
                    getRemoteShops()
                } else {
                    Single.just(cachedShops)
                        .map(DataShopInfoMapper::mapToDomain)
                }
            }
    }

    override fun updateShop(shop: DomainShopInfo): Completable {
        return localDataSource.updateShop(shop.mapToLocal())
    }

    override fun deleteAllShops(): Completable {
        return localDataSource.deleteAll()
    }

    override fun getGoods(shopId: Int): Single<List<DomainShop>> {
        return remoteDataSource.getGoods(shopId)
            .map { DataShopMapper.mapToDomain(it) }
    }

    private fun getRemoteShops(): Single<List<DomainShopInfo>> {
        return remoteDataSource.getShops()
            .flatMap { shops ->
                localDataSource.insertAllShops(shops)
                    .andThen(
                        Single.just(shops).map(DataShopInfoMapper::mapToDomain)
                    )
            }
    }

}