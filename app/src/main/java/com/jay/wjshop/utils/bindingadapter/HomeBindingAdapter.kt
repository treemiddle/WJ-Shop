package com.jay.wjshop.utils.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.jay.common.makeLog
import com.jay.wjshop.model.Shop
import com.jay.wjshop.ui.base.WJBaseListener
import com.jay.wjshop.ui.home.product.ProductFragment
import com.jay.wjshop.ui.home.product.ProductPagerAdapter

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

@BindingAdapter(
    "setViewPagerListener",
    "setShopList",
    "setPagerAdapter"
)
fun ViewPager2.bindViewPagerListener(
    listener: WJBaseListener.WJViewPagerListener?,
    shops: List<Shop>?,
    pagerAdapter: ProductPagerAdapter?
) {
    pagerAdapter?.let { adapter = it }
    removeFragment(pagerAdapter)
    addFragment(this, shops, pagerAdapter, listener)

    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            try {
                val view = (getChildAt(0) as RecyclerView).layoutManager?.findViewByPosition(position)

                view?.let {
                    val width = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
                    val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                    it.measure(width, height)

                    if (layoutParams.height != view.measuredHeight) {
                        layoutParams = (layoutParams).also { lp ->
                            lp.height = view.measuredHeight
                        }
                    }
                }

                listener?.onPageSelected(position)
            } catch (e: Exception) {
                makeLog(javaClass.simpleName, "onPageSelected: ${e.localizedMessage}")
                e.printStackTrace()
            }
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

private fun addFragment(
    viewPager: ViewPager2,
    shops: List<Shop>?,
    pagerAdapter: ProductPagerAdapter?,
    listener: WJBaseListener.WJViewPagerListener?
) {
    if (shops != null && pagerAdapter != null) {
        shops.forEachIndexed { index, _ ->
            pagerAdapter.add(ProductFragment.newInstance(index))
        }

        listener?.pageLimit(shops.size)
        moveToFirstPage(viewPager)
    }
}

private fun removeFragment(pagerAdapter: ProductPagerAdapter?) {
    pagerAdapter?.let {
        if (it.itemCount > 0) it.clear()
    }
}

private fun moveToFirstPage(viewPager: ViewPager2) {
    viewPager.currentItem = 0
}