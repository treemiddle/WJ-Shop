package com.jay.wjshop.ui.base

interface WJBaseListener {

    interface WJTabLayoutListener {
        fun onTabSelected(currentPosition: Int)
    }

    interface WJViewPagerListener {
        fun onPageSelected(currentPosition: Int)
        fun pageLimit(shopSize: Int)
    }
}