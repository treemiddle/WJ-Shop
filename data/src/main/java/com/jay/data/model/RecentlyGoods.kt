package com.jay.data.model

data class DataPerson(
    val parent: DataParentEntity,
    val childList: List<DataChildEntity>
)

data class DataParentEntity(
    val parentId: Int,
    val parentName: String
)

data class DataChildEntity(
    val inParentId: Int,
    val childId: Int,
    val childName: String
)