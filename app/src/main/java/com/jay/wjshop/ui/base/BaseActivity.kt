package com.jay.wjshop.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VDB : ViewDataBinding, VM : WJBaseViewModel>(
    @LayoutRes
    private val layoutResId: Int
) : AppCompatActivity() {

    protected val binding by lazy { DataBindingUtil.setContentView<VDB>(this, layoutResId) }
    protected abstract val viewModel: VM
    protected val compositeDisposable by lazy(::CompositeDisposable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply { lifecycleOwner = this@BaseActivity }

        setupBinding()
        setupObserver()
    }

    abstract fun setupBinding()

    abstract fun setupObserver()

    protected fun initViews() {

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}