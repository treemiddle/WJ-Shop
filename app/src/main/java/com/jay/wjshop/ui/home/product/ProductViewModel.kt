package com.jay.wjshop.ui.home.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.domain.model.DomainGoods
import com.jay.domain.usecase.LocalUseCase
import com.jay.wjshop.mapper.mapToDomain
import com.jay.wjshop.model.Goods
import com.jay.wjshop.model.Shop
import com.jay.wjshop.model.ShopInfo
import com.jay.wjshop.model.ShopSales
import com.jay.wjshop.ui.base.WJBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val localUseCase: LocalUseCase
) : WJBaseViewModel() {

    private val goodsAndShopInfoSubject = BehaviorSubject.create<Pair<ShopSales, ShopInfo>>()

    private val _products = MutableLiveData<Shop>()
    val product: LiveData<Shop>
        get() = _products

    init {
        registerRx()
    }

    fun setProducts(shop: Shop) {
        _products.value = shop
    }

    fun getProducts(): Shop? {
        return _products.value
    }

    fun onClickGoods(item: Pair<ShopSales, ShopInfo?>) {
        item.second?.let { goodsAndShopInfoSubject.onNext(item.first to it) } ?: return
    }

    private fun getRecentlyGoods() = goodsAndShopInfoSubject.value!!

    private fun saveGoods() {
        localUseCase.insertGoods(createNewGoods())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "saveGoods success")
            }, {
                makeLog(javaClass.simpleName, "saveGoods fail: ${it.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun createNewGoods(): DomainGoods {
        val item = getRecentlyGoods()

        return Goods(
            shopId = item.second.id,
            id = item.first.id,
            name = item.first.name,
            imageUrl = item.first.imageUrl,
            isPreOrder = item.first.isPreOrder,
            isSoldOut = item.first.isSoldOut,
            originalPrice = item.first.originalPrice,
            salePrice = item.first.salePrice
        ).mapToDomain()
    }

    private fun registerRx() {
        compositeDisposable.addAll(
            goodsAndShopInfoSubject.throttleFirst(750L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { saveGoods() }
        )
    }

}