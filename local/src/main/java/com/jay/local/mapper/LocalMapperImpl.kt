package com.jay.local.mapper

import com.jay.data.model.DataGoods
import com.jay.data.model.DataShopAndGoods
import com.jay.data.model.DataShopInfo
import com.jay.local.model.GoodsEntity
import com.jay.local.model.ShopAndGoodsEntity
import com.jay.local.model.ShopEntity

fun DataGoods.mapToLocal(): GoodsEntity {
    return GoodsEntity(
        shopId = this.shopId,
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        isPreOrder = this.isPreOrder,
        isSoldOut = this.isSoldOut,
        originalPrice = this.originalPrice,
        salePrice = this.salePrice,
        time = System.currentTimeMillis()
    )
}

fun GoodsEntity.mapToData(): DataGoods {
    return DataGoods(
        shopId = this.shopId,
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        isPreOrder = this.isPreOrder,
        isSoldOut = this.isSoldOut,
        originalPrice = this.originalPrice,
        salePrice = this.salePrice
    )
}

fun DataShopAndGoods.mapToLocal(): ShopAndGoodsEntity {
    return ShopAndGoodsEntity(
        shop = ShopEntity(
            id = this.shop.id,
            imageUrl = this.shop.imageUrl,
            shortName = this.shop.shortName,
            name = this.shop.name,
            type = this.shop.type,
            time = System.currentTimeMillis()
        ),
        goodsList = this.goodsList.map {
            GoodsEntity(
                shopId = it.shopId,
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                isPreOrder = it.isPreOrder,
                isSoldOut = it.isSoldOut,
                originalPrice = it.originalPrice,
                salePrice = it.salePrice,
                time = System.currentTimeMillis()
            )
        }
    )
}

fun ShopAndGoodsEntity.mapToData(): DataShopAndGoods {
    return DataShopAndGoods(
        shop = DataShopInfo(
            id = this.shop.id,
            imageUrl = this.shop.imageUrl,
            shortName = this.shop.shortName,
            name = this.shop.name,
            type = this.shop.type,
            time = System.currentTimeMillis()
        ),
        goodsList = this.goodsList.map {
            DataGoods(
                shopId = it.shopId,
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                isPreOrder = it.isPreOrder,
                isSoldOut = it.isSoldOut,
                originalPrice = it.originalPrice,
                salePrice = it.salePrice
            )
        }
    )
}