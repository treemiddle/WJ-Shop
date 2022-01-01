package com.jay.data.model

data class DataShopAndGoods(
    val shop: DataShopInfo,
    val goodsList: List<DataGoods>
)

data class DataGoods(
    val shopId: Int,
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isPreOrder: Boolean,
    val isSoldOut: Boolean,
    val originalPrice: String,
    val salePrice: String
)