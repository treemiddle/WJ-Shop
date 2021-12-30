package com.jay.wjshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.AppBarLayout
import com.jay.wjshop.databinding.ActivityWjBinding
import com.jay.wjshop.utils.ext.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WJActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWjBinding
    private val viewModel by viewModels<WJViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupObserver()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wj)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun setupObserver() {
        with(viewModel) {
            shopList.observe(this@WJActivity, {
                binding.shopInfo = it.first()
                binding.executePendingBindings()
            })
            toast.observe(this@WJActivity, {
                shortToast(it)
            })
        }
    }

}