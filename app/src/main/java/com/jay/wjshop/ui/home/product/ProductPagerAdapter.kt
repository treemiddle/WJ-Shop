package com.jay.wjshop.ui.home.product

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@SuppressLint("NotifyDataSetChanged")
class ProductPagerAdapter @Inject constructor(
    context: Context
) : FragmentStateAdapter(context as FragmentActivity) {

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


//@SuppressLint("NotifyDataSetChanged")
//@ActivityScoped
//class ProductPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
//
//    private val fragments = mutableListOf<Fragment>()
//    private val hashId = fragments.map { it.hashCode().toLong() }
//
//    override fun getItemCount() = fragments.size
//
//    override fun createFragment(position: Int): Fragment {
//        return ProductFragment.newInstance(position)
//    }
//
//    override fun getItemId(position: Int): Long {
//        return fragments[position].hashCode().toLong()
//    }
//
//    override fun containsItem(itemId: Long): Boolean {
//        return hashId.contains(itemId)
//    }
//
//    fun add(fragment: Fragment) {
//        fragments.add(fragment)
//        notifyDataSetChanged()
//    }
//
//    fun remove(position: Int) {
//        fragments.removeAt(position)
//        notifyDataSetChanged()
//    }
//
//    fun clear() {
//        fragments.clear()
//        notifyDataSetChanged()
//    }
//
//}