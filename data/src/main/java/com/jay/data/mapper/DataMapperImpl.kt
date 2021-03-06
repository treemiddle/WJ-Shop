package com.jay.data.mapper

import com.jay.data.model.DataGoods
import com.jay.data.model.DataShopAndGoods
import com.jay.data.model.DataShopInfo
import com.jay.domain.model.DomainGoods
import com.jay.domain.model.DomainShopAndGoods
import com.jay.domain.model.DomainShopInfo

fun DomainGoods.mapToData(): DataGoods {
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

fun DataGoods.mapToDomain(): DomainGoods {
    return DomainGoods(
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

fun DataShopAndGoods.mapToDomain(): DomainShopAndGoods {
    return DomainShopAndGoods(
        shop = DomainShopInfo(
            id = this.shop.id,
            imageUrl = this.shop.imageUrl,
            shortName = this.shop.shortName,
            name = this.shop.name,
            type = this.shop.type
        ),
        goodsList = this.goodsList.map {
            DomainGoods(
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

fun DomainShopAndGoods.mapToData(): DataShopAndGoods {
    return DataShopAndGoods(
        shop = DataShopInfo(
            id = this.shop.id,
            imageUrl = this.shop.imageUrl,
            shortName = this.shop.shortName,
            name = this.shop.name,
            type = this.shop.type
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