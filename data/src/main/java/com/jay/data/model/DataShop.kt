package com.jay.data.model

data class DataShop(
    val id: Int,
    val category: String,
    val salesList: List<DataSales>
)

data class DataSales(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isPreOrder: Boolean,
    val isSoldOut: Boolean,
    val originalPrice: Int,
    val salePrice: Int
)