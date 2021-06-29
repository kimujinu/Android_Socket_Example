package com.example.question_and_answer.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * ViewModel이 상속받을 ViewModelBasic
 *
 * 다른 ViewModel은 이제 ViewModelBasic을 상속받게 된다.
 *
 * 해당 ViewModelBasic를 만든 이유 :  Activity가 참조할 ViewModel을 만들고 그러한 ViewModel을 View가 참조하고, Databinding을 수행하도록 만들어야 한다.
 *                                 기본적으로 ViewModel은 android.lifeCycle.ViewModel을 상속받으면 끝이긴하지만, 그외에 할 일이 많기 때문에
 *                                 android.lifeCycle.ViewModel을 상속받는 ViewModelBasic을 만드는것이 코드효율이 좋기 때문이다.
 */
open class ViewModelBase :ViewModel() {

    /**
     * RxJava의 Observing을 위한 부분 addDisposable을 이용하여 추가해야한다.
     *
     * Model에서 들어오는 Single<>과 같은 RxJava 객체들의 Observing을 위한 부분
     * 기본적으로 RxJava의 Observable들은 compositeDisposable에 추가 해주고
     * ViewModel이 없어질 때 추가했던 것들을 지워줘야 한다.
     */
    private val compositeDisposable = CompositeDisposable()

    protected var TAG = javaClass.simpleName
    protected var _msg = MutableLiveData<String>()
    val msg: LiveData<String> get() = _msg

    /**
     * Observable들을 옵저빙할때 addDisposable()을 쓰게된다.
     * compositeDisposable라는 곳에 추가하고 ViewModel이 사라질 때 (onCleared)가 추가한 것들을 다 지우는 방식으로 작동한다.
     * onCleared를 사용하는 이유는 Observing을 계속하면 메모리 누수가 생길 위험이 있기 때문이다.
     * 때문에 데이터 구독을 시작하며 compositeDisposable에 추가하고 Observing을 그만두게 될 때 compositeDisposable를 비워줌으로써
     * 메모리 누수를 방지한다.
     */
    //구독 등록
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    /**
     * ViewModel은 View와 lifeCycle을 공유하기 때문에 View가 onDestory()될 때,
     * ViewModel의 onCleared()가 호출되게 되며, 그에 따라 Observable들이 전부 없어지게 된다.
     */
    //구독취소 subscribe 취소시킴 (메모리 관리)
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}