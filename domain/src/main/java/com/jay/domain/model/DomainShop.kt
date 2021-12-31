package com.jay.domain.model

data class DomainShop(
    val id: Int,
    val category: String,
    val salesList: List<DomainSales>
)

data class DomainSales(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isPreOrder: Boolean,
    val isSoldOut: Boolean,
    val originalPrice: String,
    val salePrice: String
)