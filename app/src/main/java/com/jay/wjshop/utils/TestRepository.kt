//package com.jay.wjshop.utils
//
//import com.jay.common.ChildEntity
//import com.jay.common.ParentEntity
//import com.jay.common.Person
//import io.reactivex.Completable
//import io.reactivex.Single
//
//interface TestRepository {
//
//    fun insertParent(parentEntity: ParentEntity): Completable
//
//    fun getParents(): Single<List<ParentEntity>>
//
//    fun clearParents(): Completable
//
//    fun insertChild(childEntity: ChildEntity): Completable
//
//    fun getChilds(): Single<List<ChildEntity>>
//
//    fun clearChilds(): Completable
//
//    fun getChildByParentId(parentId: Int): Single<List<Person>>
//
//}