package com.jay.wjshop.utils.bindingadapter

import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout
import com.jay.wjshop.model.Shop
import com.jay.wjshop.ui.base.WJBaseListener

@BindingAdapter("setTabLayoutListener", "setShopList")
fun TabLayout.bindTabLayoutListener(listener: WJBaseListener.WJTabLayoutListener?, shops: List<Shop>?) {
    removeTabItem(this)
    addTabItem(this, shops)

    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
        override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let { listener?.onTabSelected(it.position) }
        }
    })
}

private fun addTabItem(tabLayout: TabLayout, shops: List<Shop>?) {
    shops?.let {
        it.forEachIndexed { index, _ ->
            val tab = tabLayout.newTab().setText(it[index].category)
            tabLayout.addTab(tab)
        }
    }

    moveToFirstTab(tabLayout)
}

private fun removeTabItem(tabLayout: TabLayout) {
    if (tabLayout.tabCount > 0) {
        tabLayout.removeAllTabs()
    }
}

private fun moveToFirstTab(tabLayout: TabLayout) {
    if (tabLayout.tabCount > 0) {
        tabLayout.selectTab(tabLayout.getTabAt(0))
    }
}