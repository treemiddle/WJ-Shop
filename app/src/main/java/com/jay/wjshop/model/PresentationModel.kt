package com.jay.wjshop.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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
)

data class RecentlyGoods(
    val shopId: Int,
    val categoryId: Int,
    val goods: ShopSales
)