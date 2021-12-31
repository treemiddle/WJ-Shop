package com.jay.wjshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
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
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wj)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun setupObserver() {
        with(viewModel) {
            goodsList.observe(this@WJActivity, {
                it?.let { shops -> setupPagerAdapter(shops) }
            })
            toast.observe(this@WJActivity, {
                shortToast(it)
            })
        }
    }

    private fun initPagerAdapter() {
        binding.viewPager.adapter = viewPagerAdapter
    }

    private fun setupPagerAdapter(shops: List<Shop>) {
        deleteAll()
        addFragment(shops)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = shops[position].category
        }.attach()
    }

    private fun addFragment(shops: List<Shop>) {
        for (i in shops.indices) {
            viewPagerAdapter.add(CategoryFragment.newInstance(i))
        }
    }

    private fun deleteAll() {
        binding.tabLayout.removeAllTabs()
        viewPagerAdapter.clear()
    }

}