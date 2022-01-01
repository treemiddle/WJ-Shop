package com.jay.data.local

import com.jay.common.TestChildEntity
import com.jay.common.TestParentEntity
import com.jay.common.TestPerson
import com.jay.data.model.DataShopInfo
import io.reactivex.Completable
import io.reactivex.Single

interface WJLocalDataSource {

    fun insertAllShops(shops: List<DataShopInfo>): Completable

    fun getShops(): Single<List<DataShopInfo>>

    fun updateShop(shop: DataShopInfo): Completable

    fun deleteAll(): Completable

    //todo Test
    /**
     * Test
     */
    fun insertParent(parentEntity: TestParentEntity): Completable

    fun getParents(): Single<List<TestParentEntity>>

    fun clearParents(): Completable

    fun insertChild(childEntity: TestChildEntity): Completable

    fun getChilds(): Single<List<TestChildEntity>>

    fun clearChilds(): Completable

    fun getChildByParentId(parentId: Int): Single<TestPerson>
}