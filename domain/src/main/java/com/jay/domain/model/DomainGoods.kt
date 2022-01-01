package com.jay.domain.model

data class DomainShopAndGoods(
    val shop: DomainShopInfo,
    val goodsList: List<DomainGoods>
)

data class DomainGoods(
    val shopId: Int,
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isPreOrder: Boolean,
    val isSoldOut: Boolean,
    val originalPrice: String,
    val salePrice: String
)