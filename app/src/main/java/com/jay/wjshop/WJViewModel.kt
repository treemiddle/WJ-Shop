package com.jay.wjshop

import androidx.lifecycle.ViewModel
import com.jay.domain.usecase.WJUseCase
import com.jay.wjshop.mapper.shopInfoMapToPresentation
import com.jay.wjshop.mapper.shopMapToPresentation
import com.jay.wjshop.utils.makeLog
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class WJViewModel @Inject constructor(private val wjUseCase: WJUseCase) : ViewModel() {

    private val compositeDisposable by lazy(::CompositeDisposable)

    init {
        //getShopInfo()
        getShop()
    }

    private fun getShopInfo() {
        wjUseCase.getShopInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.shopInfoMapToPresentation() }
            .subscribe({
                makeLog(javaClass.simpleName, "$it")
            }, { t ->
                makeLog(javaClass.simpleName, "shop info error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun getShop() {
        wjUseCase.getShop(1)
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.shopMapToPresentation() }
            .subscribe({
                makeLog(javaClass.simpleName, "$it")
            }, { t ->
                makeLog(javaClass.simpleName, "shop error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}