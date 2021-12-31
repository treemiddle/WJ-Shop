package com.jay.wjshop.utils

import com.jay.wjshop.model.Shop
import com.jay.wjshop.model.ShopInfo
import com.jay.wjshop.model.ShopSales

fun dummyShops(): List<ShopInfo> {
    return listOf(
        ShopInfo(
            id = 1,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/82/TXT_Logotipo.png",
            name = "TOMORROW X TOGETHER",
            type = "GLOBAL",
            shortName = "TXT"
        ),
        ShopInfo(
            id = 2,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
            name = "BTS",
            type = "GLOBAL",
            shortName = "BTS"
        ),
        ShopInfo(
            id = 3,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
            name = "2022 WEVERSE CON",
            type = "GLOBAL",
            shortName = "2022 WEVERSE CON"
        ),
        ShopInfo(
            id = 4,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
            name = "2022 WEVERSE CON",
            type = "KOREA",
            shortName = "2022 WEVERSE CON"
        ),
        ShopInfo(
            id = 5,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
            name = "GFRIEND",
            type = "GLOBAL",
            shortName = "GFRIEND"
        ),ShopInfo(
            id = 6,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
            name = "GFRIEND",
            type = "KOREA",
            shortName = "GFRIEND"
        ),
        ShopInfo(
            id = 7,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
            name = "Cherry Bullet",
            type = "GLOBAL",
            shortName = "Cherry Bullet"
        ),
        ShopInfo(
            id = 8,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
            name = "Cherry Bullet",
            type = "KOREA",
            shortName = "Cherry Bullet"
        ),
        ShopInfo(
            id = 9,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
            name = "BLACKPINK",
            type = "GLOBAL",
            shortName = "BLACKPINK"
        ),
        ShopInfo(
            id = 10,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
            name = "BLACKPINK",
            type = "KOREA",
            shortName = "BLACKPINK"
        )
    )
}

fun dummyGoods(): List<Shop> {
    return listOf(
        Shop(
            id = 1,
            category = "test1",
            salesList = listOf(
                ShopSales(
                    id = 1,
                    name = "1-test1",
                    imageUrl = "asdf",
                    isPreOrder = false,
                    isSoldOut = true,
                    originalPrice = 500,
                    salePrice = 2000
                ),
                ShopSales(
                    id = 2,
                    name = "1-test2",
                    imageUrl = "asdf",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 14000,
                    salePrice = 20060
                ),
                ShopSales(
                    id = 3,
                    name = "1-test3",
                    imageUrl = "asdf",
                    isPreOrder = false,
                    isSoldOut = false,
                    originalPrice = 4000,
                    salePrice = 2000
                ),
                ShopSales(
                    id = 4,
                    name = "1-test4",
                    imageUrl = "asdf",
                    isPreOrder = false,
                    isSoldOut = true,
                    originalPrice = 300000,
                    salePrice = 22033
                )
            )
        ),
        Shop(
            id = 2,
            category = "test2",
            salesList = listOf(
                ShopSales(
                    id = 1,
                    name = "2-test1",
                    imageUrl = "asdf",
                    isPreOrder = true,
                    isSoldOut = false,
                    originalPrice = 12000,
                    salePrice = 20300
                ),
                ShopSales(
                    id = 2,
                    name = "2-test2",
                    imageUrl = "asdf",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 145000,
                    salePrice = 9000
                )
            )
        ),
        Shop(
            id = 3,
            category = "test3",
            salesList = listOf(
                ShopSales(
                    id = 1,
                    name = "3-test1",
                    imageUrl = "asdf",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 103300,
                    salePrice = 9000
                ),
                ShopSales(
                    id = 2,
                    name = "3-test2",
                    imageUrl = "asdf",
                    isPreOrder = false,
                    isSoldOut = false,
                    originalPrice = 978300,
                    salePrice = 509340
                ),
                ShopSales(
                    id = 3,
                    name = "3-test3",
                    imageUrl = "asdf",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 98000,
                    salePrice = 5900
                )
            )
        )
    )
}