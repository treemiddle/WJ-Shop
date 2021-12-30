package com.jay.data.mapper

import com.jay.data.model.DataShopInfo
import com.jay.domain.model.DomainShopInfo

object DataShopInfoMapper : DataMapper<List<DataShopInfo>, List<DomainShopInfo>> {

    override fun mapToDomain(from: List<DataShopInfo>): List<DomainShopInfo> {
        return from.map {
            DomainShopInfo(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name,
                type = it.type,
                shortName = it.shortName
            )
        }
    }

    override fun mapToData(from: List<DomainShopInfo>): List<DataShopInfo> {
        return from.map {
            DataShopInfo(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name,
                type = it.type,
                shortName = it.shortName
            )
        }
    }

}