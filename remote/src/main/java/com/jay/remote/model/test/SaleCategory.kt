package com.jay.remote.model.test

data class SaleCategory(
    val id: Int,
    val name: String,
    val sales: List<Sale>
)