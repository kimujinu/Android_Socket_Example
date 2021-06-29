package com.example.question_and_answer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * ActivityBase<ActivitySBSMainBinding>
 * 와 같이 상속받을 때 ActivitySBSMainBinding 과 같은 파일이 자동생성되지 않는다면
 * 확인사항
 * 1. 해당 Activity의 레이아웃이 <layout></layout>으로 감싸져 있는지 확인
 * 2. ReBulid or Clean 후 다시 빌드
 * 3. 이름확인 sbs_main_activity -> ActivitySbsMainBinding
 */
//모든 Activity는 해당 ActivityBase를 구현하게 된다.
abstract class ActivityBase<T : ViewDataBinding,VM: ViewModelBase>: AppCompatActivity() {
    val TAG = javaClass.simpleName

    /**
     * setContentView로 호출할 Layout의 리소스 Id
     * ex) R.layout.activity_sbs_main
     */
    abstract val layoutResourceId: Int

    /**
     * ViewDataBinding은 Activity의 layout을 빌드하면 자동 생성되는 클래스
     * 하지만 그냥 빌드한다고 생성되는 것은 아니고 layout에 한가지 작업이 필요하다.
     * ex) .xml파일에 <layout></layout>으로 감싸야한다.
     */
    lateinit var viewDataBinding: T

    /**
     * ViewModel로 쓰일 변수
     * Activity가 Activity를 구현할 때, ViewDataBinding 클래스 뿐만 아니라 ViewModel 클래스도 제네릭으로 주게된다.
     */
    abstract val viewModel : VM

    /**
     * layout을 띄운 직후 호출
     * View나 Activity의 속성 등을 초기화
     * ex) RecyclerView, toolbar, Darwerview ...
     */
    protected abstract fun init()

    /**
     * 레이아웃의 액션호출 담당 함수
     * 화면이 띄어져 있는 동안은 지속적으로 생존해 있는 함수라고 생각하면 된다.
     * ex)setOnOptionButtonClickListener, addOnTabSelectedListener ...
     */
    protected abstract fun initListener()

    /**
     * Databinding 및 RxJava설정.
     * ex) RxJava observe, databinding observe ...
     */
    protected abstract fun initDatabinding()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
        viewDataBinding.lifecycleOwner=this

        init()
        initListener()
        initDatabinding()
    }





}