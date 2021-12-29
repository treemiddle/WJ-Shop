package com.jay.data.remote

import com.jay.data.model.DataShop
import com.jay.data.model.DataShopInfo
import io.reactivex.Single

interface WJRemoteDataSource {

    fun getShopInfo(): Single<List<DataShopInfo>>

    fun getShop(shopId: Int): Single<List<DataShop>>
}