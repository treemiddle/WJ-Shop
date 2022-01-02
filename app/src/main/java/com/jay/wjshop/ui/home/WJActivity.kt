package com.jay.wjshop.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.jay.wjshop.R
import com.jay.wjshop.databinding.ActivityWjBinding
import com.jay.wjshop.model.Shop
import com.jay.wjshop.ui.home.product.ProductFragment
import com.jay.wjshop.ui.home.product.ProductPagerAdapter
import com.jay.wjshop.utils.EventObserver
import com.jay.wjshop.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WJActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWjBinding
    private val viewModel by viewModels<WJViewModel>()
    private val viewPagerAdapter by lazy { ProductPagerAdapter(this) }
    private val recentlyGoodsAdapter by lazy { RecentlyGoodsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupObserver()
        initRecentlyGoodsAdapter()
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
            productList.observe(this@WJActivity, {
                it?.let { shops -> setTabAndPagers(shops) }
            })
            recentlyGoodsList.observe(this@WJActivity, {
                val newList = it.take(8)
                recentlyGoodsAdapter.submitList(newList)
                binding.rvRecently.smoothScrollToPosition(0)
            })
            toast.observe(this@WJActivity, EventObserver {
                shortToast(
                    "0에서 ${viewModel.getShopInfoList().lastIndex}까지 랜덤하게 뽑습니다." +
                            "중복 값은 발행하지 않아요."
                )
            })
        }
    }

    private fun setTabAndPagers(shops: List<Shop>) {
        removeTabItem()
        removeFragment()
        addTabItem(shops)
        addFragment(shops)
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

        moveToFirstTab()
    }

    private fun removeTabItem() {
        if (binding.tabLayout.tabCount > 0) {
            binding.tabLayout.removeAllTabs()
        }
    }

    private fun moveToFirstTab() {
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0))
    }

    private fun moveToFirstPager() {
        binding.viewPager.currentItem = 0
    }


    private fun addFragment(shops: List<Shop>) {
        shops.forEachIndexed { index, _ ->
            viewPagerAdapter.add(ProductFragment.newInstance(index))
        }

        moveToFirstPager()
    }

    private fun removeFragment() {
        if (viewPagerAdapter.itemCount > 0) {
            viewPagerAdapter.clear()
        }
    }

    private fun initRecentlyGoodsAdapter() {
        binding.rvRecently.adapter = recentlyGoodsAdapter
    }

}