package com.jay.remote.model.test

data class Sale(
    val id: Int,
    val imageUrl: String,
    val isPreOrder: Boolean,
    val isSoldOut: Boolean,
    val name: String,
    val originalPrice: Int,
    val salePrice: Int
)