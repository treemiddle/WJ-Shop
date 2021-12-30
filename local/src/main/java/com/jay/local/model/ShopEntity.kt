package com.jay.local.model

import androidx.room.DatabaseView
import androidx.room.Entity
import androidx.room.PrimaryKey

@DatabaseView
@Entity(tableName = "shop")
data class ShopEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imageUrl: String,
    val shortName: String,
    val name: String,
    val type: String,
    val time: Long
)