package com.jay.wjshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jay.common.makeLog
import com.jay.domain.usecase.LocalUseCase
import com.jay.domain.usecase.WJUseCase
import com.jay.wjshop.mapper.ShopInfoMapper
import com.jay.wjshop.mapper.ShopInfoMapper.mapToDomain
import com.jay.wjshop.mapper.ShopMapper
import com.jay.wjshop.model.ShopInfo
import com.jay.wjshop.utils.dummyShops
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class WJViewModel @Inject constructor(
    private val wjUseCase: WJUseCase,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val compositeDisposable by lazy(::CompositeDisposable)
    private val headerClickSubject = PublishSubject.create<Unit>()
    private val localShopSubject = BehaviorSubject.create<List<ShopInfo>>()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _headerShopName = MutableLiveData<String?>()
    val headerShopName: LiveData<String?>
        get() = _headerShopName

    private val _headerType = MutableLiveData<String?>()
    val headerType: LiveData<String?>
        get() = _headerType

    private val _shopList = MutableLiveData<List<ShopInfo>>()
    val shopList: LiveData<List<ShopInfo>>
        get() = _shopList

    // mutalbelivedata 바꿔 테스트용임 지금
    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    init {
        registerRx()
        getLocalShops()
    }

    fun onHeaderClick() = headerClickSubject.onNext(Unit)

    private fun localShopList(shops: List<ShopInfo>) = localShopSubject.onNext(shops)

    private fun showLoading() {
        _isLoading.value = true
    }

    private fun hideLoading() {
        _isLoading.value = false
    }

    private fun setShopList(shopInfos: List<ShopInfo>) {
        _shopList.value = shopInfos

        setHeaderData()
    }

    private fun getShopList(): List<ShopInfo> {
        return _shopList.value ?: emptyList()
    }

    private fun setHeaderData() {
        val shop = getShopList().first()

        _headerShopName.value = shop.name
        _headerType.value = shop.type
        getGoods(shop.id)
    }

    private fun changeHeaderShopName() {
        if (getShopList().isEmpty()) return

        val random = (0..getShopList().lastIndex).random()
        val shop = getShopList()[random]

        _toast.value = "0에서 ${getShopList().lastIndex}까지 랜덤하게 이름 뽑아요~"
        _headerShopName.value = shop.name
        _headerType.value = shop.type
        updateShop(shop)
        getGoods(shop.id)
    }

    private fun registerRx() {
        compositeDisposable.addAll(
            headerClickSubject.throttleFirst(750, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { changeHeaderShopName() },

            localShopSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        setShopList(it)
                    } else {
                        getShops()
                    }
                }
        )
    }

    // 샵리스트를 가져옴
    private fun getShops() {
        // 통신안할려고 셋
        setShopList(dummyShops())
//        wjUseCase.getShops()
//            .observeOn(AndroidSchedulers.mainThread())
//            .map(ShopInfoMapper::mapToPresentation)
//            .doOnSubscribe { showLoading() }
//            .doAfterSuccess { hideLoading() }
//            .subscribe({
//                makeLog(javaClass.simpleName, "$it")
//                setShopList(it)
//            }, { t ->
//                makeLog(javaClass.simpleName, "shop info error: ${t.localizedMessage}")
//            }).addTo(compositeDisposable)
    }

    // 선택된 샵의 따라 굿즈리스트를 가져옴
    private fun getGoods(shopId: Int) {
//        wjUseCase.getGoods(shopId)
//            .observeOn(AndroidSchedulers.mainThread())
//            .map(ShopMapper::mapToPresentation)
//            .subscribe({
//                makeLog(javaClass.simpleName, "$it")
//            }, { t ->
//                makeLog(javaClass.simpleName, "shop error: ${t.localizedMessage}")
//            }).addTo(compositeDisposable)
    }

    // 선택된 샵을 로컬에 저장
    private fun updateShop(shop: ShopInfo) {
        localUseCase.updateShop(shop.mapToDomain())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "shop insert success")
            }, { t ->
                makeLog(javaClass.simpleName, "shop insert error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    // 테스트용으로 만듦. (로컬의 저장된 샵을 전체 지움)
    private fun deleteAllShops() {
        localUseCase.deleteAllShops()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "shop delete all success")
            }, { t ->
                makeLog(javaClass.simpleName, "shop delete all error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    // 로컬에 있는 shop 정보를 가져옴
    private fun getLocalShops() {
        localUseCase.getShops()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(ShopInfoMapper::mapToPresentation)
            .subscribe({
                makeLog(javaClass.simpleName, "local shops: $it")
                localShopList(it)
            }, { t ->
                makeLog(javaClass.simpleName, "local shops error: ${t.localizedMessage}")
                localShopList(emptyList())
            }).addTo(compositeDisposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}