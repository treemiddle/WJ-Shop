package com.jay.wjshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.domain.usecase.LocalUseCase
import com.jay.domain.usecase.WJUseCase
import com.jay.wjshop.mapper.ShopInfoMapper
import com.jay.wjshop.mapper.ShopInfoMapper.mapToDomain
import com.jay.wjshop.mapper.mapToPresentation
import com.jay.wjshop.model.Shop
import com.jay.wjshop.model.ShopInfo
import com.jay.wjshop.ui.base.WJBaseViewModel
import com.jay.wjshop.utils.dummyGoods1
import com.jay.wjshop.utils.dummyGoods2
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
class WJViewModel @Inject constructor(
    private val wjUseCase: WJUseCase,
    private val localUseCase: LocalUseCase
) : WJBaseViewModel() {

    private val headerClickSubject = PublishSubject.create<Unit>()
    private val localShopSubject = BehaviorSubject.create<List<ShopInfo>>()
    private var disposable: Disposable? = null

    private val _headerShopName = MutableLiveData<String?>()
    val headerShopName: LiveData<String?>
        get() = _headerShopName

    private val _headerType = MutableLiveData<String?>()
    val headerType: LiveData<String?>
        get() = _headerType

    private val _shopList = MutableLiveData<List<ShopInfo>>()
    val shopList: LiveData<List<ShopInfo>>
        get() = _shopList

    private val _goodsList = MutableLiveData<List<Shop>?>()
    val goodsList: LiveData<List<Shop>?>
        get() = _goodsList

    private val _currentShop = MutableLiveData<ShopInfo>()
    val currentShop: LiveData<ShopInfo>
        get() = _currentShop

    // mutalbelivedata 바꿔 테스트용임 지금
    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    // 테스트 인덱스용
    private val _testIndex = MutableLiveData(0)

    init {
        registerRx()
        deleteShopAndGoods()
        getLocalShops()
        getShopAndGoods()
    }

    fun onHeaderClick() = headerClickSubject.onNext(Unit)

    private fun localShopList(shops: List<ShopInfo>) = localShopSubject.onNext(shops)

    private fun setShopList(shopInfos: List<ShopInfo>) {
        makeLog(javaClass.simpleName, "이거 뭐지??: $shopInfos")
        _shopList.value = shopInfos

        setHeaderData()
    }

    fun getShopList(): List<ShopInfo> {
        return _shopList.value ?: emptyList()
    }

    fun getGoodsList(): List<Shop> {
        return _goodsList.value ?: emptyList()
    }

    fun getCurrentShop(): ShopInfo? {
        return _currentShop.value
    }

    private fun setHeaderData() {
        val shop = getShopList().first()

        _currentShop.value = shop
        _headerShopName.value = shop.name
        _headerType.value = shop.type
        getGoods(shop.id)
    }

    private fun changeHeaderShopName() {
        if (getShopList().isEmpty()) return

//        val random = (0..getShopList().lastIndex).random()
//        val shop = getShopList()[random]

        val index = _testIndex.value
        val shop = getShopList()[index!!]

        //_toast.value = "0에서 ${getShopList().lastIndex}까지 랜덤하게 이름 뽑아요~"
        _currentShop.value = shop
        _headerShopName.value = shop.name
        _headerType.value = shop.type
        updateShop(shop)
        getGoods(shop.id)

        if (index == 0) {
            _testIndex.value = 1
        } else {
            _testIndex.value = 0
        }
    }

    private fun registerRx() {
        compositeDisposable.addAll(
            headerClickSubject.throttleFirst(750, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    makeLog(javaClass.simpleName, "들어옴 헤더클릭")
                    changeHeaderShopName()
                }, {
                    makeLog(javaClass.simpleName, "들어옴 헤더: ${it.localizedMessage}")
                }),

            localShopSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    makeLog(javaClass.simpleName, "뭐지??????????: $it")
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
        wjUseCase.getShops()
            .observeOn(AndroidSchedulers.mainThread())
            .map(ShopInfoMapper::mapToPresentation)
            .doOnSubscribe { showLoading() }
            .doAfterSuccess { hideLoading() }
            .subscribe({
                makeLog(javaClass.simpleName, "$it")
                setShopList(it)
            }, { t ->
                makeLog(javaClass.simpleName, "shop info error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    // 선택된 샵의 따라 굿즈리스트를 가져옴
    private fun getGoods(shopId: Int) {
        if (shopId == 1) {
            _goodsList.value = dummyGoods1()
        } else {
            _goodsList.value = dummyGoods2()
        }
//        disposable?.let {
//            if (!it.isDisposed) {
//                it.dispose()
//            }
//        }
//
//        disposable = wjUseCase.getGoods(shopId)
//            .observeOn(AndroidSchedulers.mainThread())
//            .map(ShopMapper::mapToPresentation)
//            .subscribe({
//                makeLog(javaClass.simpleName, "들어옴 remote ${it.size}")
//                _goodsList.value = it
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

    //todo 성공이나 init에 들어가기떄문에 shopId를 동적으로 어케 줄것이냐에 생각하기
    private fun getShopAndGoods() {
        val shopdId = (1..2).random()
        localUseCase.getGoodsByShopId(shopdId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.mapToPresentation() }
            .subscribe {
                makeLog(javaClass.simpleName, "상품보고있어!!!")
                makeLog(javaClass.simpleName, "size: ${it.goodsList.size}, shopdId: ${it.shop.id}")
            }
            .addTo(compositeDisposable)
    }

    private fun deleteShopAndGoods() {
        localUseCase.clearGoods()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                makeLog(javaClass.simpleName, "goods delete success")
            }, {
                makeLog(javaClass.simpleName, "goods delete fail: ${it.localizedMessage}")
            }).addTo(compositeDisposable)
    }

}