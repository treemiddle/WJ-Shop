package com.jay.local.mapper

import com.jay.data.model.DataShopInfo
import com.jay.local.model.ShopEntity

object ShopInfoMapper : LocalMapper<DataShopInfo, ShopEntity> {

    override fun mapToData(from: ShopEntity): DataShopInfo {
        return DataShopInfo(
            id = from.id,
            imageUrl = from.imageUrl,
            name = from.name,
            type = from.type,
            shortName = from.shortName
        )
    }

    override fun mapToEntity(from: DataShopInfo): ShopEntity {
        return ShopEntity(
            id = from.id,
            imageUrl = from.imageUrl,
            name = from.name,
            type = from.type,
            shortName = from.shortName,
            time = System.currentTimeMillis()
        )
    }

    fun List<ShopEntity>.toListData(): List<DataShopInfo> {
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

    fun List<DataShopInfo>.toListEntity(): List<ShopEntity> {
        return this.map {
            ShopEntity(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name,
                type = it.type,
                shortName = it.shortName,
                time = System.currentTimeMillis()
            )
        }
    }

}