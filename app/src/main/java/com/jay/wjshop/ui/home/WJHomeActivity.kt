package com.jay.wjshop.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.jay.common.makeLog
import com.jay.wjshop.R
import com.jay.wjshop.databinding.ActivityHomeBinding
import com.jay.wjshop.model.Goods
import com.jay.wjshop.model.Shop
import com.jay.wjshop.ui.base.BaseActivity
import com.jay.wjshop.ui.base.WJBaseListener
import com.jay.wjshop.ui.home.product.ProductFragment
import com.jay.wjshop.ui.home.product.ProductPagerAdapter
import com.jay.wjshop.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint

/**
 * [WJHomeActivity]
 * 선언형식이 아닌 펑션들 다 바인딩으로 빼야함..
 */
@AndroidEntryPoint
class WJHomeActivity : BaseActivity<ActivityHomeBinding, WJHomeViewModel>(R.layout.activity_home), WJBaseListener.WJTabLayoutListener {

    override val viewModel: WJHomeViewModel by viewModels()
    private val viewPagerAdapter by lazy { ProductPagerAdapter(this) }
    private val recentlyGoodsAdapter by lazy { RecentlyGoodsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingShimmer(true)
        initRecentlyGoodsAdapter()
        initPagerAdapter()
    }

    override fun setupBinding() {
        binding.vm = viewModel
        binding.listener = this
    }

    override fun setupObserver() {
        with(viewModel) {
            productList.observe(this@WJHomeActivity, {
                it?.let { shops ->
                    binding.shops = shops
                    binding.executePendingBindings()

                    setTabAndPagers(shops)
                }
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

    override fun onTabSelected(currentPosition: Int) = with(binding) {
        nsv.smoothScrollTo(0, 0)
        viewPager.currentItem = currentPosition
    }

    private fun setTabAndPagers(shops: List<Shop>) {
        removeFragment()
        addFragment(shops)
        setContentView(true)
        loadingShimmer(false)
    }

    private fun initPagerAdapter() = with(binding) {
        viewPager.adapter = viewPagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                try {
                    val view = (viewPager.getChildAt(0) as RecyclerView).layoutManager?.findViewByPosition(position)

                    view?.let {
                        val width = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
                        val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                        it.measure(width, height)

                        if (viewPager.layoutParams.height != view.measuredHeight) {
                            viewPager.layoutParams = (viewPager.layoutParams).also { lp ->
                                lp.height = view.measuredHeight
                            }
                        }
                    }

                    tabLayout.selectTab(tabLayout.getTabAt(position))
                    nsv.smoothScrollTo(0, 0)
                } catch (e: Exception) {
                    makeLog(javaClass.simpleName, "onPageSelected: ${e.localizedMessage}")
                    e.printStackTrace()
                }
            }
        })
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