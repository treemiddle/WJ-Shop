package com.jay.data.model

data class DataShopInfo(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val type: String,
    val shortName: String,
    val time: Long = System.currentTimeMillis()
)
