package com.jay.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.local.dao.ShopDao
import com.jay.local.dao.TestDao
import com.jay.local.model.ChildEntity
import com.jay.local.model.ParentEntity
import com.jay.local.model.ShopEntity

@Database(entities = [ShopEntity::class, ParentEntity::class, ChildEntity::class], exportSchema = false, version = 1)
abstract class WJDatabase : RoomDatabase() {
    abstract fun shopDao(): ShopDao
    abstract fun testDao(): TestDao
}