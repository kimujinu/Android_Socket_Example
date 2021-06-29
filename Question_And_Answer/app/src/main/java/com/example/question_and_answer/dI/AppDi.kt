package com.example.question_and_answer.dI


import com.google.android.gms.common.util.CollectionUtils.listOf
import org.koin.dsl.module.module


/**
 * DI(Dependency Injection) : Android는 context의 영향을 많이 받는 플랫폼이다.
 *                            예를 들면 Activity Lifecycle에 따라 자원을 생성하고 사용 할 수 있다.
 *                            즉, Activity, Fragment내에서 선언되고 사용되는 Instance들은
 *                            Activity, Fragment의 영향을 받는다는 말이다.
 *                            만약 Instance 생성 시 내부 환경의 영향을 받는다면, 같은 Instance라도 다른 환경에서 다르게 동작 할 수 있다.
 *                            하지만 항상 같은 환경에서만 Instance를 생성하고, Activity나 Fragment에서는 생성된 Instance를 받아서 사용만 한다면,
 *                            내부 환경과는 상관없이 같은 동작을 하며, 범용적으로 재사용 할 수 있다.
 *                            이러한 개념을 DI(Dependency Injection) 라고 한다.
 *
 * DI(Dependency Injection) 사용목적 : 객체 간의 의존관계를 객체-객체가 아닌 외부에서 객체를 생성하고 전달해줌으로써 객체간의 의존성 제거 및 결합도를 낮추기 위해 사용
 * DI(Dependency Injection) 장점 : 모듈간의 의존성이 낮아져 유지보수 및 코드 재사용에 좋다, 테스트에 용이하다.
 *
 * 현재 사용하고 있는 DI라이브러리를 Koin이라고 한다. 그 밖에도 Dagger2..등등 더 있다..하지만 Koin이 구현이 간단하므로 사용하는것 같다..
 *
 *  DSL(Domain Specific Language) : 도메인 특화 언어를 말하는데 밑의 소스코드를 보면 module, factory, viewModel이 쓰였는데 해당 언어를 DSL이라고 한다.
 *
 *  Koin DSL 키워드 : module : Koin모듈 정의
 *                   viewModel : Activity나 Fragment에 각 ViewModel을 주입
 *                   factory : 의존성 주입(inject/get) 시점마다 새로운 객체를 매번 생성
 *                   single : 해당 객체를 싱글톤으로 생성(App LifeCycle 전체동안 단일 인스턴스
 *                   bind : 생성할 객체를 다른 Type으로 바인딩(Class, Interface 상속관계 필요)
 *                   get() : Component 내에서 알맞은 의존성 주입
 *                   inject() : get과 같이 알맞은 의존성 주입(by inject()방식, val에만 가능, var변수에 사용불가)
 *
 *  현 어플 Koin 동작방식 : 1. Module 선언(생성)
 *                       2. GlobalApplication에서 startKoin()으로 Koin 실행
 *                       3. 의존성 주입 - 구성요소(Activity, Fragment, Class 등등)
 */

val app= module {
    factory {

    }

}

val viewModels = module {

}

val DiModule = listOf(app,viewModels)
//다른곳에서 get() 이나 by inject()를 통해 원하는 서비스를 얻어올수 있다.

/*
    DSL이란 : DSL(Domain Specific Language)의 약어, 도메인 특화 언어
    Koin DSL 키워드 : - module : koin 모듈 정의
                     - ViewModel : Activity나 Fragment에 각 ViewModel을 주입
                     - factory : 의존성 주입(inject/get) 시점마다 새로운 객체를 매번 생성
                     - single : 해당 객체를 싱글톤으로 생성 (App LifeCycle 전체동안 단일 인스턴스)
                     - bind : 생성할 객체를 다른 Type으로 바인딩(Class, Interface 상속관계 필요)
                     - get() : Component 내에서 알맞은 의존성 주입
                     - inject() : get과 같이 알맞은 의존성 주입(by inject()방식, val에만 가능, var변수에 사용불가)
     Koin 동작방식 :  1. Module선언(생성)
                     2. Application 단위 Class에서 startKoin()으로 Koin 실행
                     3. 의존성 주입 - 구성요소(Activity, Fragment, Class 등)
 */