package com.jay.data.remote

import com.jay.data.model.DataShop
import com.jay.data.model.DataShopInfo
import io.reactivex.Single

interface WJRemoteDataSource {

    fun getShops(): Single<List<DataShopInfo>>

    fun getGoods(shopId: Int): Single<List<DataShop>>
}