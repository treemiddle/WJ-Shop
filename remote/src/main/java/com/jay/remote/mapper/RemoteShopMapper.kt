package com.jay.remote.mapper

import com.jay.common.toPrice
import com.jay.data.model.DataSales
import com.jay.data.model.DataShop
import com.jay.remote.model.shop.Sale
import com.jay.remote.model.shop.SaleCategories
import com.jay.remote.model.shop.ShopResponse

object RemoteShopMapper : RemoteMapper<ShopResponse, List<DataShop>> {

    override fun mapToData(from: ShopResponse): List<DataShop> {
        return from.saleCategories.map {
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
                        originalPrice = sale.originalPrice.toPrice(),
                        salePrice = sale.salePrice.toPrice()
                    )
                }
            )
        }
    }

    override fun mapToRemote(from: List<DataShop>): ShopResponse {
        return ShopResponse(
            saleCategories = from.map {
                SaleCategories(
                    id = it.id,
                    name = it.category,
                    sales = it.salesList.map { sale ->
                        Sale(
                            id = sale.id,
                            name = sale.name,
                            imageUrl = sale.imageUrl,
                            isPreOrder = sale.isPreOrder,
                            isSoldOut = sale.isSoldOut,
                            originalPrice = sale.originalPrice.toInt(),
                            salePrice = sale.salePrice.toInt()
                        )
                    }
                )
            }
        )
    }

}