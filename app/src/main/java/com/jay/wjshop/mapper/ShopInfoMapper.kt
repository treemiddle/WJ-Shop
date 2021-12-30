package com.jay.wjshop.mapper

import com.jay.domain.model.DomainShopInfo
import com.jay.wjshop.model.ShopInfo

object ShopInfoMapper : PresentationMapper<List<DomainShopInfo>, List<ShopInfo>> {

    override fun mapToDomain(from: List<ShopInfo>): List<DomainShopInfo> {
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

    override fun mapToPresentation(from: List<DomainShopInfo>): List<ShopInfo> {
        return from.map {
            ShopInfo(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name,
                type = it.type,
                shortName = it.shortName
            )
        }
    }

}