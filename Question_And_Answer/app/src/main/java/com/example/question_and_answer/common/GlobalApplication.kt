package com.example.question_and_answer.common

import android.app.Application
import com.example.question_and_answer.dI.DiModule
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module

class GlobalApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext,DiModule)
    }
}