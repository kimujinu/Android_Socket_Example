package com.example.question_and_answer.view

import com.example.question_and_answer.R
import com.example.question_and_answer.base.FragmentBase
import com.example.question_and_answer.databinding.FragmentMainBinding
import com.example.question_and_answer.viewModel.FragMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : FragmentBase<FragmentMainBinding,FragMainViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_main

    override val viewModel: FragMainViewModel by viewModel()


    override fun init() {

    }

    override fun initListener() {
    }

    override fun initLiveData() {
    }

}