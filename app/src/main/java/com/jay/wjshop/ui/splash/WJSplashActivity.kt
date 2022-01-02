package com.jay.wjshop.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.jay.wjshop.R
import com.jay.wjshop.databinding.ActivitySplashBinding
import com.jay.wjshop.ui.base.BaseActivity
import com.jay.wjshop.ui.home.WJHomeActivity
import com.jay.wjshop.utils.removeAnimation
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class WJSplashActivity : BaseActivity<ActivitySplashBinding, WJSplashViewModel>(R.layout.activity_splash) {

    override val viewModel: WJSplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        removeAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            startHome()
        }, 2500)
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserver() {
        with(viewModel) {

        }
    }

    private fun startHome() {
        Intent(this, WJHomeActivity::class.java).apply {
            startActivity(this)
        }
    }
}