package com.jay.wjshop.mapper

import com.jay.domain.model.DomainSales
import com.jay.domain.model.DomainShop
import com.jay.wjshop.model.Shop
import com.jay.wjshop.model.ShopSales

object ShopMapper : PresentationMapper<List<DomainShop>, List<Shop>> {

    override fun mapToDomain(from: List<Shop>): List<DomainShop> {
        return from.map {
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

    override fun mapToPresentation(from: List<DomainShop>): List<Shop> {
        return from.map {
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

}