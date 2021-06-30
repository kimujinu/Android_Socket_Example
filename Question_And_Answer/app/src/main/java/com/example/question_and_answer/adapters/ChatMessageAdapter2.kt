package com.example.question_and_answer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.question_and_answer.R
import com.example.question_and_answer.common.Define
import com.example.question_and_answer.models.ChatMessage
import kotlinx.android.synthetic.main.item_received_message.view.*
import kotlinx.android.synthetic.main.item_self_message.view.*
import kotlinx.android.synthetic.main.item_system_message.view.*
import java.util.*

class ChatMessageAdapter2 : RecyclerView.Adapter<ChatMessageAdapter2.ViewHolder>{

    private val SYSTEM_VIEW = 0
    private val SELF_VIEW = 1
    private val RECEIVED_VIEW = 2

    var mContext :Context?

    var mData: ArrayList<ChatMessage>? = null

    constructor(context: Context) {
        this.mContext = context
    }

    fun addItems(item: ChatMessage?) {
        if (mData == null) {
            mData = ArrayList()
        }
        mData!!.add(item!!)
        notifyDataSetChanged()
    }

    open inner class ViewHolder: RecyclerView.ViewHolder {
        constructor(view:View) : super(view) {
        }
    }

    inner class SystemHolder : ViewHolder {
        constructor(view: View) : super(view){

        }
    }

    inner class SelfHolder : ViewHolder{
        constructor(view: View) : super(view){

        }
    }

    inner class ReceivedHolder : ViewHolder{
        constructor(view: View) : super(view){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view: View?
        when (viewType) {
            SYSTEM_VIEW -> {
                view = inflater.inflate(R.layout.item_system_message, parent, false)
//                view = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_system_message,parent,false)
                return SystemHolder(view)
            }
            SELF_VIEW -> {
                view = inflater.inflate(R.layout.item_self_message, parent, false)
//                view = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_self_message,parent,false)
                return SelfHolder(view)
            }
            //RECEIVED_VIEW -> {
            else -> {
                view = inflater.inflate(R.layout.item_received_message, parent, false);
//                view = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_received_message,parent,false)
                return ReceivedHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var viewType = getItemViewType(position)
        var item : ChatMessage = mData!!.get(position)

        if (viewType == SYSTEM_VIEW) {
            val systemHolder : SystemHolder = holder as SystemHolder
            val messageSuffix = if (item.userAction == "entered") "님이 채팅방에 들어왔습니다." else "님이 채팅방을 나갔습니다."

            systemHolder.itemView.messageTextView1.text = item.messageOwner + messageSuffix
        } else if (viewType == SELF_VIEW) {
            val selfHolder : SelfHolder = holder as SelfHolder

            selfHolder.itemView.messageTextView2.text = item.messageContent
        } else if (viewType == RECEIVED_VIEW) {
            val receivedHolder : ReceivedHolder = holder as ReceivedHolder

            receivedHolder.itemView.messageOwnerTextView.text = item.messageOwner
            receivedHolder.itemView.messageTextView3.text = item.messageContent
        }
    }

    override fun getItemCount(): Int {
        return if (mData == null) 0 else mData!!.size
    }

    override fun getItemViewType(position: Int): Int {

        var currentItem : ChatMessage = mData!!.get(position)
        var messageType = currentItem.messageType

        if (messageType == Define.MESSAGE_TYPE_SYSTEM) {
            return SYSTEM_VIEW
        } else if (messageType == Define.MESSAGE_TYPE_SELF) {
            return SELF_VIEW
        } else if (messageType == Define.MESSAGE_TYPE_RECEIVE) {
            return RECEIVED_VIEW
        }
        return super.getItemViewType(position)
    }

}