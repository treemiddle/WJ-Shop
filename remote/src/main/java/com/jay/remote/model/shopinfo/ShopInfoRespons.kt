package com.jay.remote.model.shopinfo

data class ShopInfoRespons(
    val shopInfos: List<ShopInfo>
)

data class ShopInfo(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val type: String
)