package com.jay.wjshop.ui.home.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.domain.model.DomainGoods
import com.jay.domain.usecase.LocalUseCase
import com.jay.wjshop.mapper.mapToDomain
import com.jay.wjshop.model.*
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

    private val _salesList = MutableLiveData<List<ShopSalesModel>>()
    val salesList: LiveData<List<ShopSalesModel>>
        get() = _salesList

    init {
        registerRx()
    }

    fun setProducts(shop: Shop) {
        _products.value = shop

        newShopSalesList(shop.salesList)
    }

    fun getProducts(): Shop? {
        return _products.value
    }

    fun onClickGoods(item: Pair<ShopSales, ShopInfo?>) {
        item.second?.let { goodsAndShopInfoSubject.onNext(item.first to it) } ?: return
    }

    fun getRecentlyGoods() = goodsAndShopInfoSubject.value!!

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

    private fun createShopSales(): ShopSalesButton {
        return ShopSalesButton()
    }

    private fun newShopSalesList(salesList: List<ShopSales>) {
        val newList = mutableListOf<ShopSalesModel>().apply {
            addAll(salesList)
            add(createShopSales())
        }

        _salesList.value = newList
    }

    private fun registerRx() {
        compositeDisposable.addAll(
            goodsAndShopInfoSubject.throttleFirst(200, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { saveGoods(); showToast(2) }
        )
    }

}