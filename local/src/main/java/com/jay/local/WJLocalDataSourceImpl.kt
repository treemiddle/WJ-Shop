package com.jay.local

import com.jay.common.TestChildEntity
import com.jay.common.TestParentEntity
import com.jay.common.TestPerson
import com.jay.data.local.WJLocalDataSource
import com.jay.data.model.DataShopInfo
import com.jay.local.dao.ShopDao
import com.jay.local.dao.TestDao
import com.jay.local.mapper.ShopInfoMapper.mapToEntity
import com.jay.local.mapper.ShopInfoMapper.toListData
import com.jay.local.mapper.ShopInfoMapper.toListEntity
import com.jay.local.mapper.mapToEntity
import com.jay.local.mapper.mapToTestChildList
import com.jay.local.mapper.mapToTestParentList
import com.jay.local.mapper.mapToTestPerson
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class WJLocalDataSourceImpl @Inject constructor(
    private val shopDao: ShopDao,
    private val testDao: TestDao
) : WJLocalDataSource {

    override fun insertAllShops(shops: List<DataShopInfo>): Completable {
        return shopDao.insertAllShops(shops.toListEntity())
    }

    override fun getShops(): Single<List<DataShopInfo>> {
        return shopDao.getShops()
            .map { it.toListData() }
    }

    override fun updateShop(shop: DataShopInfo): Completable {
        return shopDao.updateShop(mapToEntity(shop))
    }

    override fun deleteAll(): Completable {
        return shopDao.deleteAll()
    }

    //todo Test
    /**
     * Test
     */
    override fun insertParent(parentEntity: TestParentEntity): Completable {
        return testDao.insertParent(parentEntity.mapToEntity())
    }

    override fun getParents(): Single<List<TestParentEntity>> {
        return testDao.getParents().map { it.mapToTestParentList() }
    }

    override fun clearParents(): Completable {
        return testDao.clearParents()
    }

    override fun insertChild(childEntity: TestChildEntity): Completable {
        return testDao.insertChild(childEntity.mapToEntity())
    }

    override fun getChilds(): Single<List<TestChildEntity>> {
        return testDao.getChilds().map { it.mapToTestChildList() }
    }

    override fun clearChilds(): Completable {
        return testDao.clearChilds()
    }

    override fun getChildByParentId(parentId: Int): Single<TestPerson> {
        return testDao.getChildByParentId(parentId).map { it.mapToTestPerson() }
    }
}