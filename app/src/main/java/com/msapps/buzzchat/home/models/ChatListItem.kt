package com.msapps.buzzchat.home.models

data class ChatListItem(
    val imageUrl: String,
    val name: String,
    val lastMessage: String,
    val lastMessageTimeStamp: Int,
    val unreadMessagesCount: Int
)
