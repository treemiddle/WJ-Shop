package com.jay.remote.mapper

import com.jay.data.model.DataShopInfo
import com.jay.remote.model.shopinfo.RemoteShopInfo
import com.jay.remote.model.shopinfo.ShopInfoRespons

object RemoteShopInfoMapper : RemoteMapper<ShopInfoRespons, List<DataShopInfo>> {

    override fun mapToData(from: ShopInfoRespons): List<DataShopInfo> {
        return from.shopInfos.map {
            DataShopInfo(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name,
                type = it.type,
                shortName = it.shortName
            )
        }
    }

    override fun mapToRemote(from: List<DataShopInfo>): ShopInfoRespons {
        return ShopInfoRespons(
            shopInfos = from.map {
                RemoteShopInfo(
                    id = it.id,
                    imageUrl = it.imageUrl,
                    name = it.name,
                    type = it.type,
                    shortName = it.shortName
                )
            }
        )
    }

}