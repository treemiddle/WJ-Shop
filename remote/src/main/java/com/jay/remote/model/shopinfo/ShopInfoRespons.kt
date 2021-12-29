package com.jay.remote.model.shopinfo

data class ShopInfoRespons(
    val shopInfos: List<RemoteShopInfo>
)

data class RemoteShopInfo(
    val id: Int,
    val imageUrl: String,
    val shortName: String,
    val name: String,
    val type: String
)