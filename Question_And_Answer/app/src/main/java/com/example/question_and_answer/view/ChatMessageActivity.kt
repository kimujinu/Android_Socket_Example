package com.example.question_and_answer.view

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.question_and_answer.R
import com.example.question_and_answer.adapters.ChatMessageAdapter
import com.example.question_and_answer.adapters.ChatMessageAdapter2
import com.example.question_and_answer.base.ActivityBase
import com.example.question_and_answer.common.Define
import com.example.question_and_answer.databinding.ActivityChatMessageBinding
import com.example.question_and_answer.models.ChatMessage
import com.example.question_and_answer.viewModel.ChatMessageViewModel
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_chat_message.*
import org.json.JSONException
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChatMessageActivity : ActivityBase<ActivityChatMessageBinding,ChatMessageViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_chat_message

    override val viewModel: ChatMessageViewModel by viewModel()

    var userName : String? = ""
    lateinit var mSocket: Socket
    lateinit var mAdapter : ChatMessageAdapter2


    @SuppressLint("ResourceAsColor")
    override fun init() {
        if (getIntent() == null) {
            finish()
            return
        }

        userName = getIntent().getStringExtra(Define.EXTRA_USERNAME);

        supportActionBar?.setTitle("HICT 채팅방")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sendButton.isEnabled = false
        sendButton.setTextColor(R.color.textDisabled)

        setupRecyclerView();
        setupSocketClient();
    }

    override fun initListener() {
        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.length > 0) {
                    sendButton.isEnabled = true
                    sendButton.setTextColor(R.color.textEnabled)
                } else {
                    sendButton.isEnabled = false
                    sendButton.setTextColor(R.color.textDisabled)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    override fun initDatabinding() {

    }

    private fun setupRecyclerView(){
        mAdapter = ChatMessageAdapter2(this)
        val layoutManager = LinearLayoutManager(this)
        messagesView.setHasFixedSize(true)
        messagesView.layoutManager = layoutManager
        messagesView.adapter = mAdapter

        messagesView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                view: View?,
                newLeft: Int,
                newTop: Int,
                newRight: Int,
                newBottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int,
            ) {
                if (newBottom < oldBottom) {
                    messagesView.postDelayed({ messagesView.smoothScrollToPosition(mAdapter.itemCount) },
                        100)
                }
            }
        })
    }
    private fun setupSocketClient() {
        mSocket = IO.socket(Define.SOCKET_URL)
        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on(Define.EVENT_SYSTEM, onMessageReceived)
        mSocket.on(Define.EVENT_MESSAGE, onMessageReceived)
        mSocket.connect()

    }
    /**
     * Socket Server 연결 Listener
     */
    private val onConnect: Emitter.Listener = object : Emitter.Listener {
        override fun call(vararg args: Any?) {
            // 서버로 전송할 데이터 생성 및 채널 입장 이벤트 보냄.
            val sendData = JSONObject()
            try {
                sendData.put(Define.SEND_DATA_USERNAME, userName)
                mSocket!!.emit(Define.EVENT_ENTERED, sendData)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Message 전달 Listener
     */
    private val onMessageReceived = object : Emitter.Listener {
        override fun call(vararg args: Any?) {
            val rcvData = args[0] as JSONObject
            val userAction = rcvData.optString("action")
            val messageType = rcvData.optString("type")
            val messageOwner = rcvData.optJSONObject("data").optString("username")
            val messageContent = rcvData.optJSONObject("data").optString("message")
            val message = ChatMessage(userAction, messageType, messageOwner, messageContent)

            runOnUiThread {
                mAdapter.addItems(message)
                messagesView.smoothScrollToPosition(mAdapter.getItemCount())
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun sendButtonTouchUp(v:View) {
        val sendMessage = messageEditText.text.toString()

        // 서버로 전송할 데이터 생성 및 메시지 이벤트 보냄.

        // 서버로 전송할 데이터 생성 및 메시지 이벤트 보냄.
        val sendData = JSONObject()
        try {
            sendData.put(Define.SEND_DATA_USERNAME, userName)
            sendData.put(Define.SEND_DATA_MESSAGE, sendMessage)
            mSocket!!.emit(Define.EVENT_MESSAGE, sendData)

            // 전송 후, EditText 초기화
            messageEditText.setText(null)

            // 본인의 메시지는 서버에서 전달받지 않고, 바로 생성한다.
            val message = ChatMessage(null, Define.MESSAGE_TYPE_SELF,
                userName, sendMessage)
            mAdapter.addItems(message)
            messagesView.smoothScrollToPosition(mAdapter.itemCount)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}





