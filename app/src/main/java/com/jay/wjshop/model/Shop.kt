package com.jay.wjshop.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Shop(
    val id: Int,
    val category: String,
    val salesList: List<ShopSales>
)

@Parcelize
data class ShopSales(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isPreOrder: Boolean,
    val isSoldOut: Boolean,
    val originalPrice: Int,
    val salePrice: Int
) : Parcelable