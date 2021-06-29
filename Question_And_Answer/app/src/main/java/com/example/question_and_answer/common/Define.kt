package com.example.question_and_answer.common

open class Define {
    companion object{
        const val SOCKET_URL = "https://cro-socket-example.herokuapp.com"
        const val EVENT_ENTERED = "join-user"
        const val EVENT_SYSTEM = "system"
        const val EVENT_MESSAGE = "chat-message"

        const val SEND_DATA_USERNAME = "username"
        const val SEND_DATA_MESSAGE = "message"

        const val MESSAGE_TYPE_SYSTEM = "system"
        const val MESSAGE_TYPE_SELF = "self"
        const val MESSAGE_TYPE_RECEIVE = "received"

        const val EXTRA_USERNAME = "username"

        const val SYSTEM_VIEW = 0
        const val SELF_VIEW = 1
        const val RECEIVED_VIEW = 2
    }
}