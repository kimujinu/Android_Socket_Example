package com.example.question_and_answer.holder

import android.view.View
import com.example.question_and_answer.adapters.CustomViewHolder
import kotlinx.android.extensions.LayoutContainer


class exampleViewHolder (val view: View, private var mListener: ((View, Int) -> Unit)?, var mCallback: ((Int) -> Unit)?, private var mLongListener: ((View, Int) -> Unit)? )

    : CustomViewHolder(view), View.OnClickListener, LayoutContainer{

    override val containerView: View?
        get() = view


    override fun init(poItem: Any) {

        view.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        mListener?.let {
            it(v, adapterPosition)
        }
    }

}