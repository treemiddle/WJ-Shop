package com.jay.wjshop.ui

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Fragment>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = CategoryFragment.newInstance(position)

    @SuppressLint("NotifyDataSetChanged")
    fun add(fragment: Fragment) {
        fragments.add(fragment)
        //notifyDataSetChanged()
        notifyItemInserted(fragments.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        fragments.clear()
        notifyDataSetChanged()
    }

}