package com.jay.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.jay.local.model.ShopEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ShopDao {

    @Insert(onConflict = REPLACE)
    fun insertAllShops(shops: List<ShopEntity>): Completable

    @Query("SELECT * FROM shop ORDER BY time DESC")
    fun getShops(): Single<List<ShopEntity>>

    @Update
    fun updateShop(shop: ShopEntity): Completable

    @Query("DELETE FROM shop")
    fun deleteAll(): Completable

}