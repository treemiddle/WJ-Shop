package com.jay.wjshop.utils.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.jay.common.makeLog
import com.jay.wjshop.CategoryFragment
import com.jay.wjshop.ViewPagerAdapter
import com.jay.wjshop.WJViewModel
import com.jay.wjshop.model.Shop

//@BindingAdapter("setShopCategory", "setViewModel")
//fun bindTabLayout(
//    tabLayout: TabLayout,
//    shopList: List<Shop>?,
//    vm: WJViewModel?
//) {
//    shopList ?: return
//
//    tabLayout.removeAllTabs()
//
//    for (i in shopList.indices) {
//        val tab = tabLayout.newTab().setText(shopList[i].category)
//        tabLayout.addTab(tab)
//    }
//
//    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
//        override fun onTabReselected(tab: TabLayout.Tab?) = Unit
//        override fun onTabSelected(tab: TabLayout.Tab?) {
//            tab?.position?.let { vm?.setPagerPosition(it) }
//        }
//    })
//}
//
//@BindingAdapter("setViewModel", "setShopCategory", "setFragmentActivity")
//fun bindViewPager(
//    viewPager: ViewPager2,
//    vm: WJViewModel?,
//    shopList: List<Shop>?,
//    fragmentActivity: FragmentActivity?
//) {
//    shopList ?: return
//
//    viewPager.removeAllViews()
//
//    fragmentActivity?.let {
//        viewPager.adapter?.run {
//
//        } ?: run {
//
//            viewPager.adapter = ViewPagerAdapter(it).apply {
//                this.removeAll()
//                for (i in shopList.indices) {
//                    this.addFragment(CategoryFragment.newInstance(i))
//                }
//            }
//        }
//
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                vm?.setPagerPosition(position)
//            }
//        })
//    }
//
//}
//
//@BindingAdapter("setPosition")
//fun bindPosition(view: View, position: Int?) {
//    position ?: return
//
//    when (view) {
//        is ViewPager2 -> {
//            view.currentItem = position
//        }
//        is TabLayout -> {
//            view.run {
//                getTabAt(position)?.let { tab ->
//                    selectTab(tab)
//                }
//            }
//        }
//    }
//}