package com.jay.wjshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.wjshop.model.Shop
import com.jay.wjshop.model.ShopSales
import com.jay.wjshop.ui.base.WJBaseViewModel

class CategoryViewModel : WJBaseViewModel() {

    private val _goodsItem = MutableLiveData<Shop>()
    val goodsItem: LiveData<Shop>
        get() = _goodsItem

    fun setGoodsItem(shop: Shop) {
        _goodsItem.value = shop
    }

    fun getGoodsItem(): Shop? {
        return _goodsItem.value
    }

}