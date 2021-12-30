package com.jay.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.local.dao.ShopDao
import com.jay.local.model.ShopEntity

@Database(entities = [ShopEntity::class], exportSchema = false, version = 1)
abstract class WJDatabase : RoomDatabase() {
    abstract fun shopDao(): ShopDao
}