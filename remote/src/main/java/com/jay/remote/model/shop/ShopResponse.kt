package com.jay.remote.model.shop

data class ShopResponse(
    val saleCategories: List<SaleCategories>
)

data class SaleCategories(
    val id: Int,
    val name: String,
    val sales: List<Sale>
)

data class Sale(
    val id: Int,
    val imageUrl: String,
    val isPreOrder: Boolean,
    val isSoldOut: Boolean,
    val name: String,
    val originalPrice: Int,
    val salePrice: Int
)