package com.example.question_and_answer.view


import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.question_and_answer.R
import com.example.question_and_answer.base.ActivityBase
import com.example.question_and_answer.common.Define
import com.example.question_and_answer.databinding.ActivityMainBinding
import com.example.question_and_answer.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ActivityBase<ActivityMainBinding,MainViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel:MainViewModel by viewModel()

    @SuppressLint("ResourceAsColor")
    override fun init() {
        toolbar_base.setTitleTextColor(R.color.colorWhite)
        supportActionBar!!.title = "ULink_Chatting_Example"

        usernameInputLayout.setErrorEnabled(false)
        enteredButton.setEnabled(false)
        enteredButton.setTextColor(R.color.textDisabled)


    }

    override fun initListener() {
        usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.length > 0) {
                    enteredButton.setEnabled(true)
                    enteredButton.setTextColor(R.color.textEnabled)
                } else {
                    enteredButton.setEnabled(false)
                    enteredButton.setTextColor(R.color.textDisabled)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    override fun initDatabinding() {
    }


    fun enteredButtonTouchUp(v:View) {

        val userName = usernameEditText.text.toString()
        val intent = Intent(this, ChatMessageActivity::class.java)
        intent.putExtra(Define.EXTRA_USERNAME, userName)
        startActivity(intent)

        // 기존에 입력된 닉네임 초기화
        usernameEditText.setText(null)
    }

}

