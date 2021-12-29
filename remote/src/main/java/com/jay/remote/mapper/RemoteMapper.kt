package com.jay.remote.mapper

import com.jay.data.model.DataSales
import com.jay.data.model.DataShop
import com.jay.data.model.DataShopInfo
import com.jay.remote.model.shop.SaleCategories
import com.jay.remote.model.shopinfo.RemoteShopInfo

fun List<RemoteShopInfo>.shopInfoMapToData(): List<DataShopInfo> {
    return this.map {
        DataShopInfo(
            id = it.id,
            imageUrl = it.imageUrl,
            name = it.name,
            type = it.type,
            shortName = it.shortName
        )
    }
}

fun List<SaleCategories>.shopMapToData(): List<DataShop> {
    return this.map {
        DataShop(
            id = it.id,
            category = it.name,
            salesList = it.sales.map { sale ->
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