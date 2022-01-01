package com.jay.local.model

import androidx.room.*

data class Person(
    @Embedded val parent: ParentEntity,
    @Relation(
        parentColumn = "parentId",
        entityColumn = "inParentId"
    )
    val childList: List<ChildEntity>
)

@DatabaseView
@Entity(tableName = "parent")
data class ParentEntity(
    @PrimaryKey(autoGenerate = false)
    val parentId: Int,
    val parentName: String
)

@DatabaseView
@Entity(tableName = "child")
data class ChildEntity(
    val inParentId: Int,
    @PrimaryKey(autoGenerate = false)
    val childId: Int,
    val childName: String
)

//@DatabaseView
//@Entity(tableName = "recently_goods")
//data class RecentlyGoodsInfos(
//    @Embedded val parent: GoodsParent,
//    @Relation(
//        parentColumn = "shopId",
//        entityColumn = "id"
//    )
//    val goodsList: List<GoodsEntity>
//)
//
//@Entity
//data class GoodsParent(
//    @PrimaryKey
//    val shopId: Int,
//    val shopName: String,
//    val categoryId: Int,
//    val categoryName: String
//)
//
//@Entity
//data class GoodsEntity(
//    @PrimaryKey
//    val id: Int,
//    val name: String,
//    val imageUrl: String,
//    val isPreOrder: Boolean,
//    val isSoldOut: Boolean,
//    val originalPrice: String,
//    val salePrice: String
//)