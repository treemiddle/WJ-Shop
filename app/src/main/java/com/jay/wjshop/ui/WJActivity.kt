package com.jay.wjshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jay.common.makeLog
import com.jay.wjshop.R
import com.jay.wjshop.databinding.ActivityWjBinding
import com.jay.wjshop.model.Shop
import com.jay.wjshop.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WJActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWjBinding
    private val viewModel by viewModels<WJViewModel>()
    private val viewPagerAdapter by lazy { ViewPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupObserver()
        initPagerAdapter()
        setupTabLayout()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wj)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun setupObserver() {
        with(viewModel) {
            goodsList.observe(this@WJActivity, {
                it?.let { shops ->
//                    removeAll()
//                    addFragment(shops)
//                    setupPagerAdapter(shops)
                    removeTabItem()
                    removeFragment()
                    addTabItem(shops)
                    addFragment(shops)
                }
            })
            toast.observe(this@WJActivity, {
                shortToast(it)
            })
        }
    }

    private fun initPagerAdapter() {
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }

    private fun setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { binding.viewPager.currentItem = it.position }
            }
        })
    }

    private fun addTabItem(shops: List<Shop>) {
        for (i in shops.indices) {
            val tab = binding.tabLayout.newTab().setText(shops[i].category)
            binding.tabLayout.addTab(tab)
        }
    }

    private fun removeTabItem() {
        if (binding.tabLayout.tabCount > 0) {
            binding.tabLayout.removeAllTabs()
        }
    }

    private fun addFragment(shops: List<Shop>) {
        shops.forEachIndexed { index, _ ->
            viewPagerAdapter.add(CategoryFragment.newInstance(index))
        }
    }

    private fun removeFragment() {
        if (viewPagerAdapter.itemCount > 0) {
            viewPagerAdapter.clear()
        }
    }

//    private fun setupPagerAdapter(shops: List<Shop>) {
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = shops[position].category
//        }.attach()
//    }
//
//    private fun addFragment(shops: List<Shop>) {
//        shops.forEachIndexed { index, _ ->
//            viewPagerAdapter.add(CategoryFragment.newInstance(index))
//        }
//    }
//
//    private fun removeAll() {
//        val fragmentCount = viewPagerAdapter.itemCount
//        val tabCount = binding.tabLayout.tabCount
//
//        if (fragmentCount != 0 || tabCount != 0) {
//            viewPagerAdapter.clear()
//            binding.tabLayout.removeAllTabs()
//        }
//    }

}