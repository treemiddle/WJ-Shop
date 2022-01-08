package com.jay.wjshop.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import com.jay.wjshop.R
import com.jay.wjshop.databinding.ActivityHomeBinding
import com.jay.wjshop.model.Shop
import com.jay.wjshop.ui.base.BaseActivity
import com.jay.wjshop.ui.base.WJBaseListener
import com.jay.wjshop.ui.home.product.ProductPagerAdapter
import com.jay.wjshop.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * [WJHomeActivity]
 * 선언형식이 아닌 펑션들 다 바인딩으로 빼야함..
 */
@AndroidEntryPoint
class WJHomeActivity :
    BaseActivity<ActivityHomeBinding, WJHomeViewModel>(R.layout.activity_home),
    WJBaseListener.WJTabLayoutListener,
    WJBaseListener.WJViewPagerListener,
    WJBaseListener.WJRecyclerListener
{

    override val viewModel: WJHomeViewModel by viewModels()
    @Inject lateinit var viewPagerAdapter: ProductPagerAdapter
    @Inject lateinit var goodsAdapter: RecentlyGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingShimmer(true)
        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(true)
            loadingShimmer(false)
        }, 5000)
    }

    override fun setupBinding() {
        binding.vm = viewModel
        binding.pagerAdapter = viewPagerAdapter
        binding.goodsAdapter = goodsAdapter
        binding.tabListener = this
        binding.pageListener = this
        binding.recycleListener = this
    }

    override fun setupObserver() {
        with(viewModel) {
            productList.observe(this@WJHomeActivity, {
                it?.let { shops -> setupShopDataBinding(shops) }
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

    override fun onPageSelected(currentPosition: Int) = with(binding) {
        nsv.smoothScrollTo(0, 0)
        tabLayout.selectTab(tabLayout.getTabAt(currentPosition))
    }

    override fun pageLimit(shopSize: Int) = with(binding) {
        viewPager.offscreenPageLimit = shopSize
    }

    override fun saveGoods() = with(binding) {
        rvRecently.smoothScrollToPosition(0)
    }

    private fun setupShopDataBinding(shops: List<Shop>) {
        binding.shops = shops
        binding.executePendingBindings()
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