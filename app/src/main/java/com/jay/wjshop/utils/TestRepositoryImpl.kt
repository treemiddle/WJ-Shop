//package com.jay.wjshop.utils
//
//import com.jay.common.ChildEntity
//import com.jay.common.ParentEntity
//import com.jay.common.Person
//import com.jay.local.dao.TestDao
//import io.reactivex.Completable
//import io.reactivex.Single
//import javax.inject.Inject
//
//class TestRepositoryImpl @Inject constructor(val dao: TestDao) :
//    com.jay.wjshop.utils.TestRepository {
//
//    override fun insertParent(parentEntity: ParentEntity): Completable {
//        return dao.insertParent(parentEntity)
//    }
//
//    override fun getParents(): Single<List<ParentEntity>> {
//        return dao.getParents()
//    }
//
//    override fun clearParents(): Completable {
//        return dao.clearParents()
//    }
//
//    override fun insertChild(childEntity: ChildEntity): Completable {
//        return dao.insertChild(childEntity)
//    }
//
//    override fun getChilds(): Single<List<ChildEntity>> {
//        return dao.getChilds()
//    }
//
//    override fun clearChilds(): Completable {
//        return dao.clearChilds()
//    }
//
//    override fun getChildByParentId(parentId: Int): Single<List<Person>> {
//        return dao.getChildByParentId(parentId)
//    }
//
//}