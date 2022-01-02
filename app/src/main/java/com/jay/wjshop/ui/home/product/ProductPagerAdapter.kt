package com.jay.wjshop.ui.home.product

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jay.common.makeLog

@SuppressLint("NotifyDataSetChanged")
class ProductPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Fragment>()
    private val hashId = fragments.map { it.hashCode().toLong() }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return ProductFragment.newInstance(position)
    }

    override fun getItemId(position: Int): Long {
        return fragments[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return hashId.contains(itemId)
    }

    fun add(fragment: Fragment) {
        makeLog(javaClass.simpleName, "pager adapter add")
        fragments.add(fragment)
        notifyDataSetChanged()
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