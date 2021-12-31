package com.jay.wjshop.ui

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jay.common.makeLog

@SuppressLint("NotifyDataSetChanged")
class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Fragment>()
    private val hashId = fragments.map { it.hashCode().toLong() }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = CategoryFragment.newInstance(position)

    override fun getItemId(position: Int): Long {
        return fragments[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return hashId.contains(itemId)
    }

    fun add(fragment: Fragment) {
        fragments.add(fragment)
    }

    fun remove(position: Int) {
        fragments.removeAt(position)
        notifyDataSetChanged()
    }

    fun clear() {
        fragments.clear()
        notifyDataSetChanged()
    }

}