package com.jay.wjshop.utils.bindingadapter

import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout
import com.jay.wjshop.model.Shop

@BindingAdapter("setShopCategory")
fun bindShopCategory(tabLayout: TabLayout, shopList: List<Shop>?) {
    shopList ?: return

    tabLayout.removeAllTabs()

    for (i in shopList.indices) {
        val tab = tabLayout.newTab().setText(shopList[i].category)
        tabLayout.addTab(tab)
    }
}