package com.jay.wjshop.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.common.makeLog
import com.jay.domain.usecase.LocalUseCase
import com.jay.domain.usecase.WJUseCase
import com.jay.wjshop.mapper.ShopInfoMapper
import com.jay.wjshop.mapper.ShopMapper
import com.jay.wjshop.model.Shop
import com.jay.wjshop.model.ShopInfo
import com.jay.wjshop.ui.base.WJBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class WJSplashViewModel @Inject constructor(
    private val wjUseCase: WJUseCase,
    private val localUseCase: LocalUseCase
) : WJBaseViewModel() {

    private val firstShopSubject = BehaviorSubject.create<ShopInfo>()
    private val localShopSubject = BehaviorSubject.createDefault(false)
    private val productSubject = BehaviorSubject.createDefault(false)

    private val _isFlag = MutableLiveData<Unit>()
    val isFlag: LiveData<Unit>
        get() = _isFlag

    init {
        registerRx()
        getLocalShops()
    }

    private fun registerRx() {
        combineObservable()

        compositeDisposable.addAll(
            firstShopSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe { getProducts(it.id) }
        )
    }

    private fun combineObservable() {
        Observable.combineLatest(
            localShopObservable(),
            productObservable(),
            { t1: Boolean, t2: Boolean -> t1 to t2 }
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                makeLog(javaClass.simpleName, "성공!!!!!!!!: $it")
            }
            .addTo(compositeDisposable)
    }

    private fun getProducts(shopId: Int) {
        wjUseCase.getGoods(shopId)
            .observeOn(Schedulers.computation())
            .map(ShopMapper::mapToPresentation)
            .observeOn(AndroidSchedulers.mainThread())
            .retryWhen {
                it.flatMap {
                    Flowable.timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                }
            }
            .subscribe({
                makeLog(javaClass.simpleName, "getProducts: $it")
                productSubject.onNext(true)
            }, { t ->
                makeLog(javaClass.simpleName, "getGoods error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    // 로컬에 저장된 샵의 정보들을 가져옴
    private fun getLocalShops() {
        localUseCase.getShops()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map(ShopInfoMapper::mapToPresentation)
            .observeOn(AndroidSchedulers.mainThread())
            .retryWhen {
                it.flatMap {
                    Flowable.timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                }
            }
            .subscribe({
                makeLog(javaClass.simpleName, "getLocalShops: $it")
                localShopSubject.onNext(true)
                setHeaderFirstData(it)
            }, { t ->
                makeLog(javaClass.simpleName, "getLocalShops error: ${t.localizedMessage}")
            }).addTo(compositeDisposable)
    }

    private fun setHeaderFirstData(shops: List<ShopInfo>) {
        val shopInfo = shops.first()

        firstShopSubject.onNext(shopInfo)
    }

    private fun localShopObservable(): Observable<Boolean> {
        return localShopSubject.filter { isOk -> isOk }
            .doOnNext { it }
    }

    private fun productObservable(): Observable<Boolean> {
        return productSubject.filter { isOk -> isOk }
            .doOnNext { it }
    }

}