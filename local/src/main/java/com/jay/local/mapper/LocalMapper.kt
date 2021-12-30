package com.jay.local.mapper

interface LocalMapper<D, E> {

    fun mapToData(from: E): D

    fun mapToEntity(from: D): E

}