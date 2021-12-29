package com.jay.remote.api

import com.jay.remote.model.shop.SaleCategories
import com.jay.remote.model.shop.ShopResponse
import com.jay.remote.model.shopinfo.ShopInfoRespons
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopApi {

    @GET("shopinfo")
    fun getShopInfo(): Single<ShopInfoRespons>

    @GET("shop/{ShopId}")
    fun getShop(
        @Path("ShopId") shopId: Int
    ): Single<List<SaleCategories>>

}