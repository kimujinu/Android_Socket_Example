package com.example.question_and_answer.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;

public class BaseViewHolder extends RecyclerView.ViewHolder{
    public BaseViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}