package com.jay.wjshop

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    val fragments = arrayListOf<Fragment>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment =
        CategoryFragment.newInstance(position)

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyItemInserted(fragments.size - 1)
    }

    fun removeAll() {
        fragments.removeAll(fragments)
    }

}