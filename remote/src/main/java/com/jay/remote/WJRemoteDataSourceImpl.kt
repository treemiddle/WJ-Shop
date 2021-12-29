package com.jay.remote

import com.jay.data.model.DataShop
import com.jay.data.model.DataShopInfo
import com.jay.data.remote.WJRemoteDataSource
import com.jay.remote.api.ShopApi
import com.jay.remote.mapper.shopInfoMapToData
import com.jay.remote.mapper.shopMapToData
import io.reactivex.Single
import javax.inject.Inject

class WJRemoteDataSourceImpl @Inject constructor(
    private val shopApi: ShopApi
) : WJRemoteDataSource {

    override fun getShopInfo(): Single<List<DataShopInfo>> {
        return shopApi.getShopInfo().map { it.shopInfos.shopInfoMapToData() }
    }

    override fun getShop(shopId: Int): Single<List<DataShop>> {
        return shopApi.getShop(shopId).map { it.shopMapToData() }
    }
}