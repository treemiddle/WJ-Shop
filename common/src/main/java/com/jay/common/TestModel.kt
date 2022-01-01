package com.jay.common

data class TestPerson(
    val parent: TestParentEntity,
    val childList: List<TestChildEntity>
)

data class TestParentEntity(
    val parentId: Int,
    val parentName: String
)

data class TestChildEntity(
    val inParentId: Int,
    val childId: Int,
    val childName: String
)