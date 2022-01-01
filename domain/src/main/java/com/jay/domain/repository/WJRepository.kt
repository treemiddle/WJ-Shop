package com.jay.domain.repository

import com.jay.common.TestChildEntity
import com.jay.common.TestParentEntity
import com.jay.common.TestPerson
import com.jay.domain.model.DomainShop
import com.jay.domain.model.DomainShopInfo
import io.reactivex.Completable
import io.reactivex.Single

interface WJRepository {

    fun getShops(): Single<List<DomainShopInfo>>

    fun updateShop(shop: DomainShopInfo): Completable

    fun deleteAllShops(): Completable

    fun getGoods(shopId: Int): Single<List<DomainShop>>

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