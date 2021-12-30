package com.jay.remote

import com.jay.data.model.DataShop
import com.jay.data.model.DataShopInfo
import com.jay.data.remote.WJRemoteDataSource
import com.jay.remote.api.ShopApi
import com.jay.remote.mapper.RemoteShopInfoMapper
import com.jay.remote.mapper.RemoteShopMapper
import io.reactivex.Single
import javax.inject.Inject

class WJRemoteDataSourceImpl @Inject constructor(
    private val shopApi: ShopApi
) : WJRemoteDataSource {

    override fun getShops(): Single<List<DataShopInfo>> {
        return shopApi.getShops()
            .map(RemoteShopInfoMapper::mapToData)
    }

    override fun getGoods(shopId: Int): Single<List<DataShop>> {
        return shopApi.getGoods(shopId)
            .map(RemoteShopMapper::mapToData)
    }

}