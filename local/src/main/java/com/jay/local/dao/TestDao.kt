package com.jay.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.jay.local.model.ChildEntity
import com.jay.local.model.ParentEntity
import com.jay.local.model.Person
import io.reactivex.Completable
import io.reactivex.Single

//todo Test
@Dao
interface TestDao {

    /**
     * 부모 관련
     */
    @Insert(onConflict = REPLACE)
    fun insertParent(parent: ParentEntity): Completable

    @Query("SELECT * FROM parent ORDER BY parentId DESC")
    fun getParents(): Single<List<ParentEntity>>

    @Query("DELETE FROM parent")
    fun clearParents(): Completable

    /**
     * 자식 관련
     */
    @Insert(onConflict = REPLACE)
    fun insertChild(child: ChildEntity): Completable

    @Query("SELECT * FROM child")
    fun getChilds(): Single<List<ChildEntity>>

    @Query("DELETE FROM child")
    fun clearChilds(): Completable

    /**
     * 사람 관련
     */
    @Transaction
    @Query("SELECT * FROM parent ORDER BY parentId =:parentId DESC")
    fun getChildByParentId(parentId: Int): Single<Person>

}