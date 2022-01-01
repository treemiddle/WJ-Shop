package com.jay.wjshop.mapper

import com.jay.domain.model.DomainGoods
import com.jay.domain.model.DomainShopAndGoods
import com.jay.domain.model.DomainShopInfo
import com.jay.wjshop.model.Goods
import com.jay.wjshop.model.ShopAndGoods
import com.jay.wjshop.model.ShopInfo
import com.jay.wjshop.model.ShopSales

fun DomainShopAndGoods.mapToPresentation(): ShopAndGoods {
    return ShopAndGoods(
        shop = ShopInfo(
            id = this.shop.id,
            imageUrl = this.shop.imageUrl,
            name = this.shop.name,
            type = this.shop.type,
            shortName = this.shop.shortName
        ),
        goodsList = this.goodsList.map {
            Goods(
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

fun ShopAndGoods.mapToDomain(): DomainShopAndGoods {
    return DomainShopAndGoods(
        shop = DomainShopInfo(
            id = this.shop.id,
            imageUrl = this.shop.imageUrl,
            name = this.shop.name,
            type = this.shop.type,
            shortName = this.shop.shortName
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

fun Goods.mapToDomain(): DomainGoods {
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