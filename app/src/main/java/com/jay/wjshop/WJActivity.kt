package com.jay.wjshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jay.wjshop.databinding.ActivityWjBinding
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
        setupPagerAdapter()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wj)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun setupObserver() {
        with(viewModel) {
            toast.observe(this@WJActivity, {
                shortToast(it)
            })
        }
    }


    private fun setupPagerAdapter() {
        binding.viewPager.adapter = viewPagerAdapter

        for (i in 0..9) {
            viewPagerAdapter.addFragment(CategoryFragment.newInstance(i))
        }
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = "Title $position"
//            when (position) {
////                0 -> tab.setIcon(R.drawable.ic_baseline_format_list_bulleted_24)
////                1 -> tab.setIcon(R.drawable.ic_baseline_map_24)
////                2 -> tab.setIcon(R.drawable.ic_baseline_info_24)
//            }
//        }.attach()
    }

}