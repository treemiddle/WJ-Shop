package com.jay.data.mapper

import com.jay.data.model.DataSales
import com.jay.data.model.DataShop
import com.jay.domain.model.DomainSales
import com.jay.domain.model.DomainShop

object DataShopMapper : DataMapper<List<DataShop>, List<DomainShop>> {

    override fun mapToDomain(from: List<DataShop>): List<DomainShop> {
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

    override fun mapToData(from: List<DomainShop>): List<DataShop> {
        return from.map {
            DataShop(
                id = it.id,
                category = it.category,
                salesList = it.salesList.map { sale ->
                    DataSales(
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