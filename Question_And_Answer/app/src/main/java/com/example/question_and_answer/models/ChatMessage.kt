package com.example.question_and_answer.models

data class ChatMessage(
    var userAction : String? = "",
    var messageType : String? = "",
    var messageOwner : String? = "",
    var messageContent : String? = ""
)
