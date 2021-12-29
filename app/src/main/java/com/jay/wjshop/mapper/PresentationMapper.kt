package com.jay.wjshop.mapper
import com.jay.domain.model.DomainShop
import com.jay.domain.model.DomainShopInfo
import com.jay.wjshop.model.Shop
import com.jay.wjshop.model.ShopInfo
import com.jay.wjshop.model.ShopSales

fun List<DomainShopInfo>.shopInfoMapToPresentation(): List<ShopInfo> {
    return this.map {
        ShopInfo(
            id = it.id,
            imageUrl = it.imageUrl,
            name = it.name,
            type = it.type,
            shortName = it.shortName
        )
    }
}

fun List<DomainShop>.shopMapToPresentation(): List<Shop> {
    return this.map {
        Shop(
            id = it.id,
            category = it.category,
            salesList = it.salesList.map { sale ->
                ShopSales(
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