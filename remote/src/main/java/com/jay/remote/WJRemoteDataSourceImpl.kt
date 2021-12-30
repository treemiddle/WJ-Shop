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

    override fun getShopInfo(): Single<List<DataShopInfo>> {
        return shopApi.getShopInfo()
            .map(RemoteShopInfoMapper::mapToData)
    }

    override fun getShop(shopId: Int): Single<List<DataShop>> {
        return shopApi.getShop(shopId)
            .map(RemoteShopMapper::mapToData)
    }

}