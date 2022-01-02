package com.jay.wjshop.model

import com.jay.common.ProductType

data class ShopInfo(
    val id: Int = 0,
    val imageUrl: String = "",
    val name: String = "",
    val type: String = "",
    val shortName: String = "",
)

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
) : ShopSalesModel

data class Goods(
    val shopId: Int,
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isPreOrder: Boolean,
    val isSoldOut: Boolean,
    val originalPrice: String,
    val salePrice: String
)

data class ShopAndGoods(
    val shop: ShopInfo,
    val goodsList: List<Goods>
)

data class ShopSalesButton(
    val id: Int = 0
) : ShopSalesModel

interface ShopSalesModel