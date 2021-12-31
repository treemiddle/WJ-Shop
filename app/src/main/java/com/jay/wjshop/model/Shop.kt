package com.jay.wjshop.model

data class Shop(
    val id: Int,
    val category: String,
    val salesList: List<ShopSales>
)

data class ShopSales(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isPreOrder: Boolean,
    val isSoldOut: Boolean,
    val originalPrice: String,
    val salePrice: String
)