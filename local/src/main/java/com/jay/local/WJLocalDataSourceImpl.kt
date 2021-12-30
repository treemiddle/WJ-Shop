package com.jay.local

import com.jay.data.local.WJLocalDataSource
import com.jay.data.model.DataShopInfo
import com.jay.local.dao.ShopDao
import com.jay.local.mapper.ShopInfoMapper.mapToEntity
import com.jay.local.mapper.ShopInfoMapper.toListData
import com.jay.local.mapper.ShopInfoMapper.toListEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class WJLocalDataSourceImpl @Inject constructor(
    private val shopDao: ShopDao
) : WJLocalDataSource {

    override fun insertAllShops(shops: List<DataShopInfo>): Completable {
        return shopDao.insertAllShops(shops.toListEntity())
    }

    override fun getShops(): Single<List<DataShopInfo>> {
        return shopDao.getShops()
            .map { it.toListData() }
    }

    override fun updateShop(shop: DataShopInfo): Completable {
        return shopDao.updateShop(mapToEntity(shop))
    }

    override fun deleteAll(): Completable {
        return shopDao.deleteAll()
    }

}