package com.jay.domain.usecase

import com.jay.common.TestChildEntity
import com.jay.common.TestParentEntity
import com.jay.common.TestPerson
import com.jay.domain.model.DomainShopInfo
import com.jay.domain.repository.WJRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalUseCase @Inject constructor(private val repository: WJRepository) {

    fun getShops(): Single<List<DomainShopInfo>> {
        return repository.getShops()
    }

    fun updateShop(shop: DomainShopInfo): Completable {
        return repository.updateShop(shop)
    }

    fun deleteAllShops(): Completable {
        return repository.deleteAllShops()
    }

    //todo Test
    /**
     * Test
     */
    fun insertParent(parentEntity: TestParentEntity): Completable {
        return repository.insertParent(parentEntity)
    }

    fun getParents(): Single<List<TestParentEntity>> {
        return repository.getParents()
    }

    fun clearParents(): Completable {
        return repository.clearParents()
    }

    fun insertChild(childEntity: TestChildEntity): Completable {
        return repository.insertChild(childEntity)
    }

    fun getChilds(): Single<List<TestChildEntity>> {
        return repository.getChilds()
    }

    fun clearChilds(): Completable {
        return repository.clearChilds()
    }

    fun getChildByParentId(parentId: Int): Single<TestPerson> {
        return repository.getChildByParentId(parentId)
    }

}