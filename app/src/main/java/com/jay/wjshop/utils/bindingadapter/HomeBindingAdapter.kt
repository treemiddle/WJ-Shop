package com.jay.wjshop.utils.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.jay.common.makeLog
import com.jay.wjshop.model.Goods
import com.jay.wjshop.model.Shop
import com.jay.wjshop.ui.base.WJBaseListener
import com.jay.wjshop.ui.home.RecentlyGoodsAdapter
import com.jay.wjshop.ui.home.product.ProductFragment
import com.jay.wjshop.ui.home.product.ProductPagerAdapter

@BindingAdapter("setTabLayoutListener", "setShopList")
fun TabLayout.bindTabLayoutListener(listener: WJBaseListener.WJTabLayoutListener?, shops: List<Shop>?) {
    removeTabItem(this)
    addTabItem(this, shops, listener)

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
    "setShopList"
)
fun ViewPager2.bindViewPagerListener(
    listener: WJBaseListener.WJViewPagerListener?,
    shops: List<Shop>?
) {
    removeFragment(this)
    addFragment(this, shops, listener)

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

@BindingAdapter("setGoodsList", "setRecyclerListener")
fun RecyclerView.bindGoodsAdapter(goods: List<Goods>?, listener: WJBaseListener.WJRecyclerListener?) {
    with((adapter as RecentlyGoodsAdapter)) {
        if (goods.isNullOrEmpty()) {
            this.submitList(null)
        } else {
            setRecentlyGoodsList(this@bindGoodsAdapter, goods, listener)
        }
    }
}

private fun addTabItem(
    tabLayout: TabLayout,
    shops: List<Shop>?,
    listener: WJBaseListener.WJTabLayoutListener?
) {
    shops?.let {
        it.forEachIndexed { index, _ ->
            val tab = tabLayout.newTab().setText(it[index].category)
            tabLayout.addTab(tab)
        }
    }

    moveToFirstTab(tabLayout)
    listener?.loadTabSuccess(true)
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
    listener: WJBaseListener.WJViewPagerListener?
) {
    val adapter = (viewPager.adapter as ProductPagerAdapter)

    shops?.let {
        it.forEachIndexed { index, _ ->
            adapter.add(ProductFragment.newInstance(index))
        }

        listener?.pageLimit(shops.size)
        listener?.loadPagerSuccess(true)
        moveToFirstPage(viewPager)
    }
}

private fun removeFragment(viewPager: ViewPager2) {
    (viewPager.adapter as ProductPagerAdapter).run {
        if (this.itemCount > 0) this.clear()
    }
}

private fun moveToFirstPage(viewPager: ViewPager2) {
    viewPager.currentItem = 0
}

private fun setRecentlyGoodsList(
    recyclerView: RecyclerView,
    goodsList: List<Goods>?,
    listener: WJBaseListener.WJRecyclerListener?
) {
    goodsList?.let {
        val newList = it.take(8)
        (recyclerView.adapter as RecentlyGoodsAdapter).run { this.submitList(newList) }
        listener?.saveGoods()
    }
}