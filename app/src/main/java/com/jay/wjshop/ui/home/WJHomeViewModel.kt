package com.jay.wjshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.domain.usecase.LocalUseCase
import com.jay.domain.usecase.WJUseCase
import com.jay.wjshop.mapper.ShopInfoMapper
import com.jay.wjshop.mapper.ShopInfoMapper.mapToDomain
import com.jay.wjshop.mapper.ShopMapper
import com.jay.wjshop.mapper.mapToPresentation
import com.jay.wjshop.model.Goods
import com.jay.wjshop.model.Shop
import com.jay.wjshop.model.ShopInfo
import com.jay.wjshop.ui.base.WJBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class WJHomeViewModel @Inject constructor(
    private val wjUseCase: WJUseCase,
    private val localUseCase: LocalUseCase
) : WJBaseViewModel() {

    private val headerClickSubject = PublishSubject.create<Unit>()
    private val localShopSubject = BehaviorSubject.create<List<ShopInfo>>()
    private val shopInfoSubject = BehaviorSubject.create<ShopInfo>()
    private var shopAndGoodsdisposable: Disposable? = null
    private var productDisposable: Disposable? = null

    private val _headerShopName = MutableLiveData<String?>()
    val headerShopName: LiveData<String?>
        get() = _headerShopName

    private val _headerType = MutableLiveData<String?>()
    val headerType: LiveData<String?>
        get() = _headerType

    private val _shopInfoList = MutableLiveData<List<ShopInfo>>()
    val shopInfoList: LiveData<List<ShopInfo>>
        get() = _shopInfoList

    private val _productList = MutableLiveData<List<Shop>?>()
    val productList: LiveData<List<Shop>?>
        get() = _productList

    private val _currentShop = MutableLiveData<ShopInfo>()
    val currentShop: LiveData<ShopInfo>
        get() = _currentShop

    private val _recentlyGoodsList = MutableLiveData<List<Goods>>()
    val recentlyGoodsList: LiveData<List<Goods>>
        get() = _recentlyGoodsList

    init {
        registerRx()
        getLocalShops()
    }

    fun onHeaderClick() = headerClickSubject.onNext(Unit)

    fun getProductList(): List<Shop> {
        return _productList.value ?: emptyList()
    }

    fun getCurrentShop(): ShopInfo? {
        return _currentShop.value
    }

    fun getShopInfoList(): List<ShopInfo> {
        return _shopInfoList.value ?: emptyList()
    }

    private fun localShopList(shops: List<ShopInfo>) = localShopSubject.onNext(shops)

    private fun setShopInfoSubject(shopInfo: ShopInfo) = shopInfoSubject.onNext(shopInfo)

    private fun setShopInfoList(shopInfoList: List<ShopInfo>) {
        _shopInfoList.value = shopInfoList

        setHeaderData()
    }

    private fun setCurrentShopInfo(shopInfo: ShopInfo) {
        _currentShop.value = shopInfo
        _headerShopName.value = shopInfo.name
        _headerType.value = shopInfo.type
    }

    private fun setHeaderData() {
        val shopInfo = getShopInfoList().first()

        setShopInfoSubject(shopInfo)
        setCurrentShopInfo(shopInfo)
        getProducts(shopInfo.id)
        getShopAndGoods(shopInfo.id)
    }

    private fun changeShopFromRandom() {
        if (getShopInfoList().isEmpty()) return

        val random = (0..getShopInfoList().lastIndex).random()
        val shopInfo = getShopInfoList()[random]

        setShopInfoSubject(shopInfo)
    }

    private fun setShopInfo(shopInfo: ShopInfo) {
        setCurrentShopInfo(shopInfo)
        updateShopInfo(shopInfo)
        getProducts(shopInfo.id)
        getShopAndGoods(shopInfo.id)
    }

    private fun registerRx() {
        compositeDisposable.addAll(
            headerClickSubject.throttleFirst(750, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { changeShopFromRandom() },

            localShopSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        setShopInfoList(it)
                    } else {
                        getShops()
                    }
                },

            shopInfoSubject.distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { setShopInfo(it); showToast() }
        )
    }

    // 샵리스트를 가져옴
    private fun getShops() {
        wjUseCase.getShops()
            .observeOn(AndroidSchedulers.mainThread())
            .map(ShopInfoMapper::mapToPresentation)
            .doOnSubscribe { showLoading() }
            .doAfterSuccess { hideLoading() }
            .subscribe({
                setShopInfoList(it)
            }, { t ->
                makeLog(javaClass.simpleName, "getShops error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun getProducts(shopId: Int) {
//        if (shopId == 1) {
//            _productList.value = dummyGoods1()
//        } else {
//            _productList.value = dummyGoods2()
//        }
        productDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }

        productDisposable = wjUseCase.getGoods(shopId)
            .observeOn(AndroidSchedulers.mainThread())
            .map(ShopMapper::mapToPresentation)
            .subscribe({
                _productList.value = it
            }, { t ->
                makeLog(javaClass.simpleName, "getGoods error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun updateShopInfo(shop: ShopInfo) {
        localUseCase.updateShop(shop.mapToDomain())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "updateShopInfo success")
            }, { t ->
                makeLog(javaClass.simpleName, "shop insert error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun getLocalShops() {
        localUseCase.getShops()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(ShopInfoMapper::mapToPresentation)
            .subscribe({
                localShopList(it)
            }, { t ->
                makeLog(javaClass.simpleName, "getLocalShops error: ${t.localizedMessage}")
                localShopList(emptyList())
            }).addTo(compositeDisposable)
    }

    private fun getShopAndGoods(shopId: Int) {
        shopAndGoodsdisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }

        shopAndGoodsdisposable = localUseCase.getGoodsByShopId(shopId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { showLoading() }
            .observeOn(Schedulers.computation())
            .map { it.mapToPresentation() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { hideLoading() }
            .subscribe { setRecentlyGoodsList(it.goodsList) }
            .addTo(compositeDisposable)
    }

    private fun setRecentlyGoodsList(goodsList: List<Goods>) {
        _recentlyGoodsList.value = goodsList
    }

    /**
     * [테스트용]
     * 로컬에 저장된 상품을 전부 지움(선택한 굿즈)
     */
    private fun deleteShopAndGoods() {
        localUseCase.clearGoods()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "deleteShopAndGoods success")
            }, { t ->
                makeLog(javaClass.simpleName, "deleteShopAndGoods fail: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    /**
     * [테스트용]
     * 로컬에 저장된 샵을 지움
     */
    private fun deleteAllShops() {
        localUseCase.deleteAllShops()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "deleteAllShops success")
            }, { t ->
                makeLog(javaClass.simpleName, "deleteAllShops error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

}