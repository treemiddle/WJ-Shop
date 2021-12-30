package com.jay.data.mapper

interface DataMapper<Data, Domain> {

    fun mapToDomain(from: Data): Domain

    fun mapToData(from: Domain): Data

}