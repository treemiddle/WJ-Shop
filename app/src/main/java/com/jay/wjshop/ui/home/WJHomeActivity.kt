package com.jay.wjshop.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.jay.wjshop.R
import com.jay.wjshop.databinding.ActivityHomeBinding
import com.jay.wjshop.model.Shop
import com.jay.wjshop.ui.base.BaseActivity
import com.jay.wjshop.ui.base.WJBaseListener
import com.jay.wjshop.ui.home.product.ProductPagerAdapter
import com.jay.wjshop.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject

@AndroidEntryPoint
class WJHomeActivity :
    BaseActivity<ActivityHomeBinding, WJHomeViewModel>(R.layout.activity_home),
    WJBaseListener.WJTabLayoutListener,
    WJBaseListener.WJViewPagerListener,
    WJBaseListener.WJRecyclerListener
{

    override val viewModel: WJHomeViewModel by viewModels()

    private val viewPagerAdapter by lazy { ProductPagerAdapter(this) }
    private val recentlyGoodsAdapter by lazy { RecentlyGoodsAdapter() }
    private val tabSubject = BehaviorSubject.createDefault(false)
    private val pagerSubject = BehaviorSubject.createDefault(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerRx()
        initAdapter()
    }

    override fun setupBinding() = with(binding) {
        vm = viewModel
        tabListener = this@WJHomeActivity
        pageListener = this@WJHomeActivity
        recycleListener = this@WJHomeActivity
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

    override fun loadTabSuccess(success: Boolean) = tabSubject.onNext(success)

    override fun loadPagerSuccess(success: Boolean) = pagerSubject.onNext(success)

    private fun setupShopDataBinding(shops: List<Shop>) {
        binding.shops = shops
        binding.executePendingBindings()
    }

    private fun initAdapter() = with(binding) {
        viewPager.adapter = viewPagerAdapter
        rvRecently.adapter = recentlyGoodsAdapter
    }

    private fun registerRx() {
        Observable.combineLatest(
            tabSubject,
            pagerSubject,
            { tab: Boolean, pager: Boolean -> tab to pager }
        )
            .filter { (t1: Boolean, t2: Boolean) -> t1 && t2 }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewModel.setHeaderAndType(); viewModel.setViewSuccess() }
            .addTo(compositeDisposable)
    }

}