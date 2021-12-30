package com.jay.data.mapper

import com.jay.data.model.DataShop
import com.jay.data.model.DataShopInfo
import com.jay.domain.model.DomainSales
import com.jay.domain.model.DomainShop
import com.jay.domain.model.DomainShopInfo

fun List<DataShopInfo>.shopInfoMapToDomain(): List<DomainShopInfo> {
    return this.map {
        DomainShopInfo(
            id = it.id,
            imageUrl = it.imageUrl,
            name = it.name,
            type = it.type,
            shortName = it.shortName
        )
    }
}

fun List<DataShop>.shopMapToDomain(): List<DomainShop> {
    return this.map {
        DomainShop(
            id = it.id,
            category = it.category,
            salesList = it.salesList.map { sale ->
                DomainSales(
                    id = sale.id,
                    name = sale.name,
                    imageUrl = sale.imageUrl,
                    isPreOrder = sale.isPreOrder,
                    isSoldOut = sale.isSoldOut,
                    originalPrice = sale.originalPrice,
                    salePrice = sale.salePrice
                )
            }
        )
    }
}