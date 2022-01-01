package com.jay.local.mapper

import com.jay.common.TestChildEntity
import com.jay.common.TestParentEntity
import com.jay.common.TestPerson
import com.jay.local.model.ChildEntity
import com.jay.local.model.ParentEntity
import com.jay.local.model.Person

//todo Test
fun TestParentEntity.mapToEntity(): ParentEntity {
    return ParentEntity(
        parentId = this.parentId,
        parentName = this.parentName
    )
}

fun TestChildEntity.mapToEntity(): ChildEntity {
    return ChildEntity(
        inParentId = this.inParentId,
        childId = this.childId,
        childName = this.childName
    )
}

fun TestPerson.mapToEntity(): Person {
    return Person(
        parent = ParentEntity(
            parentId = this.parent.parentId,
            parentName = this.parent.parentName
        ),
        childList = this.childList.map {
            ChildEntity(
                inParentId = it.inParentId,
                childId = it.childId,
                childName = it.childName
            )
        }
    )
}

fun List<ParentEntity>.mapToTestParentList(): List<TestParentEntity> {
    return this.map {
        TestParentEntity(
            parentName = it.parentName,
            parentId = it.parentId
        )
    }
}

fun List<ChildEntity>.mapToTestChildList(): List<TestChildEntity> {
    return this.map {
        TestChildEntity(
            inParentId = it.inParentId,
            childId = it.childId,
            childName = it.childName
        )
    }
}

fun Person.mapToTestPerson(): TestPerson {
    return TestPerson(
        parent = TestParentEntity(
            parentId = this.parent.parentId,
            parentName = this.parent.parentName
        ),
        childList = this.childList.map {
            TestChildEntity(
                inParentId = it.inParentId,
                childId = it.childId,
                childName = it.childName
            )
        }
    )
}