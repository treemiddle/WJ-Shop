package com.jay.wjshop.utils

import com.jay.common.toPrice
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
        )
    )
}

fun dummyGoods1(): List<Shop> {
    return listOf(
        Shop(
            id = 100,
            category = "TEST 1",
            salesList = listOf(
                ShopSales(
                    id = 11,
                    name = "블랙핑크",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/82/TXT_Logotipo.png",
                    isPreOrder = false,
                    isSoldOut = true,
                    originalPrice = 100.toPrice(),
                    salePrice = 100.toPrice()
                ),
                ShopSales(
                    id = 22,
                    name = "위버스",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 1400.toPrice(),
                    salePrice = 1500.toPrice()
                ),
                ShopSales(
                    id = 33,
                    name = "헤드셋",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/82/TXT_Logotipo.png",
                    isPreOrder = false,
                    isSoldOut = false,
                    originalPrice = 76495.toPrice(),
                    salePrice = 467867.toPrice()
                ),
                ShopSales(
                    id = 44,
                    name = "허니버터아몬드",
                    imageUrl = "",
                    isPreOrder = false,
                    isSoldOut = true,
                    originalPrice = 9847183.toPrice(),
                    salePrice = 95741485.toPrice()
                ),
                ShopSales(
                    id = 55,
                    name = "닭가슴살",
                    imageUrl = "",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 1345.toPrice(),
                    salePrice = 1345.toPrice()
                ),
                ShopSales(
                    id = 66,
                    name = "핫도그",
                    imageUrl = "",
                    isPreOrder = false,
                    isSoldOut = true,
                    originalPrice = 9999.toPrice(),
                    salePrice = 9999.toPrice()
                ),
                ShopSales(
                    id = 77,
                    name = "닭가슴살",
                    imageUrl = "",
                    isPreOrder = false,
                    isSoldOut = true,
                    originalPrice = 548764.toPrice(),
                    salePrice = 947124.toPrice()
                ),
                ShopSales(
                    id = 88,
                    name = "만두",
                    imageUrl = "",
                    isPreOrder = false,
                    isSoldOut = false,
                    originalPrice = 5.toPrice(),
                    salePrice = 5.toPrice()
                )
            )
        ),
        Shop(
            id = 200,
            category = "TEST 2",
            salesList = listOf(
                ShopSales(
                    id = 1,
                    name = "IOS",
                    imageUrl = "",
                    isPreOrder = true,
                    isSoldOut = false,
                    originalPrice = 123456789.toPrice(),
                    salePrice = 1234567890.toPrice()
                ),
                ShopSales(
                    id = 2,
                    name = "AOS",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/82/TXT_Logotipo.png",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 371834.toPrice(),
                    salePrice = 938491.toPrice()
                )
            )
        ),
        Shop(
            id = 300,
            category = "TEST 3",
            salesList = listOf(
                ShopSales(
                    id = 1111,
                    name = "WJ",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/82/TXT_Logotipo.png",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 103300.toPrice(),
                    salePrice = 9000.toPrice()
                ),
                ShopSales(
                    id = 2222,
                    name = "지수",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/82/TXT_Logotipo.png",
                    isPreOrder = false,
                    isSoldOut = false,
                    originalPrice = 978300.toPrice(),
                    salePrice = 509340.toPrice()
                ),
                ShopSales(
                    id = 3333,
                    name = "말왕",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/82/TXT_Logotipo.png",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 98000.toPrice(),
                    salePrice = 5900.toPrice()
                )
            )
        )
    )
}

fun dummyGoods2(): List<Shop> {
    return listOf(
        Shop(
            id = 1,
            category = "탭아이템1",
            salesList = listOf(
                ShopSales(
                    id = 9763,
                    name = "블랙핑크",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/82/TXT_Logotipo.png",
                    isPreOrder = false,
                    isSoldOut = true,
                    originalPrice = 100.toPrice(),
                    salePrice = 100.toPrice()
                ),
                ShopSales(
                    id = 3482,
                    name = "허니버터",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/BTS_logo_%282017%29.png/600px-BTS_logo_%282017%29.png",
                    isPreOrder = true,
                    isSoldOut = true,
                    originalPrice = 1400.toPrice(),
                    salePrice = 1500.toPrice()
                )
            )
        ),
        Shop(
            id = 2,
            category = "탭아이템2",
            salesList = listOf(
                ShopSales(
                    id = 98931,
                    name = "무지카드",
                    imageUrl = "",
                    isPreOrder = true,
                    isSoldOut = false,
                    originalPrice = 123456789.toPrice(),
                    salePrice = 1234567890.toPrice()
                )
            )
        )
    )
}