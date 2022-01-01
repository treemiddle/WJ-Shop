package com.jay.wjshop.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jay.wjshop.utils.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class WJBaseViewModel : ViewModel() {

    protected val compositeDisposable by lazy(::CompositeDisposable)

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    protected fun showLoading() {
        _isLoading.value = true
    }

    protected fun hideLoading() {
        _isLoading.value = false
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}