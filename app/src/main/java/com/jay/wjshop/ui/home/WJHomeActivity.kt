package com.jay.wjshop.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.jay.common.makeLog
import com.jay.wjshop.R
import com.jay.wjshop.databinding.ActivityHomeBinding
import com.jay.wjshop.model.Goods
import com.jay.wjshop.model.Shop
import com.jay.wjshop.ui.base.BaseActivity
import com.jay.wjshop.ui.home.product.ProductFragment
import com.jay.wjshop.ui.home.product.ProductPagerAdapter
import com.jay.wjshop.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint

/**
 * [WJHomeActivity]
 * 선언형식이 아닌 펑션들 다 바인딩으로 빼야함..
 */
@AndroidEntryPoint
class WJHomeActivity : BaseActivity<ActivityHomeBinding, WJHomeViewModel>(R.layout.activity_home) {

    override val viewModel: WJHomeViewModel by viewModels()
    private val viewPagerAdapter by lazy { ProductPagerAdapter(this) }
    private val recentlyGoodsAdapter by lazy { RecentlyGoodsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingShimmer(true)
        initRecentlyGoodsAdapter()
        initPagerAdapter()
        setupTabLayout()
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserver() {
        with(viewModel) {
            productList.observe(this@WJHomeActivity, {
                it?.let { shops -> setTabAndPagers(shops) }
            })
            recentlyGoodsList.observe(this@WJHomeActivity, {
                if (it.isNotEmpty()) {
                    showRecentlyGoods(true, it)
                } else {
                    showRecentlyGoods(false)
                }
            })
            toast.observe(this@WJHomeActivity, {
                it.getContentIfNotHandled()?.let {
                    shortToast(
                        "0에서 ${viewModel.getShopInfoList().lastIndex}까지 랜덤하게 뽑습니다." +
                                "중복 값은 발행하지 않아요."
                    )
                }
            })
        }
    }

    private fun setTabAndPagers(shops: List<Shop>) {
        removeTabItem()
        removeFragment()
        addTabItem(shops)
        addFragment(shops)

        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(true)
            loadingShimmer(false)
        }, 5000)
    }

    private fun initPagerAdapter() = with(binding) {
        viewPager.adapter = viewPagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
                nsv.smoothScrollTo(0, 0)
            }
        })
    }

    private fun setupTabLayout() = with(binding) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    nsv.smoothScrollTo(0, 0)
                    viewPager.currentItem = it.position
                }
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

        binding.viewPager.offscreenPageLimit = shops.size
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

    private fun showRecentlyGoods(state: Boolean, goodsList: List<Goods>? = null) {
        if (state) {
            binding.tvCurrentProduct.visibility = View.VISIBLE
            binding.rvRecently.visibility = View.VISIBLE
            setRecentlyGoodsList(goodsList)
        } else {
            recentlyGoodsAdapter.submitList(null)
            binding.tvCurrentProduct.visibility = View.GONE
            binding.rvRecently.visibility = View.GONE
        }
    }

    private fun setRecentlyGoodsList(goodsList: List<Goods>?) {
        goodsList?.let {
            val newList = it.take(8)
            recentlyGoodsAdapter.submitList(newList)
            binding.rvRecently.smoothScrollToPosition(0)
        }
    }

    private fun loadingShimmer(result: Boolean) = if (result) {
        binding.flShimmer.visibility = View.VISIBLE
    } else {
        binding.flShimmer.visibility = View.GONE
    }

    private fun setContentView(result: Boolean) = if (result) {
        binding.cdlContent.visibility = View.VISIBLE
    } else {
        binding.cdlContent.visibility = View.INVISIBLE
    }

}