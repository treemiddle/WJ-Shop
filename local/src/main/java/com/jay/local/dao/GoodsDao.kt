package com.jay.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.jay.local.model.GoodsEntity
import com.jay.local.model.ShopAndGoodsEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface GoodsDao {

    @Insert(onConflict = REPLACE)
    fun insertGoods(goods: GoodsEntity): Completable

    @Query("SELECT * FROM goods")
    fun getAllGoods(): Single<List<GoodsEntity>>

    @Query("DELETE FROM goods")
    fun clearGoods(): Completable

    @Transaction
    @Query("SELECT * FROM shop WHERE id =:shopId")
    fun getGoodsByShopId(shopId: Int): Flowable<ShopAndGoodsEntity>

}