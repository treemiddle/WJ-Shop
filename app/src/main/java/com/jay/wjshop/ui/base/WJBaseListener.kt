package com.jay.wjshop.ui.base

interface WJBaseListener {

    interface WJTabLayoutListener {
        fun onTabSelected(currentPosition: Int)
        fun loadTabSuccess(success: Boolean)
    }

    interface WJViewPagerListener {
        fun onPageSelected(currentPosition: Int)
        fun pageLimit(shopSize: Int)
        fun loadPagerSuccess(success: Boolean)
    }

    interface WJRecyclerListener {
        fun saveGoods()
    }

}