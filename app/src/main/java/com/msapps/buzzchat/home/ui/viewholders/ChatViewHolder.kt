package com.msapps.buzzchat.home.ui.viewholders

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.msapps.buzzchat.databinding.ItemChatViewHolderBinding
import com.msapps.buzzchat.home.models.ChatListItem

class ChatViewHolder(private val binding: ItemChatViewHolderBinding): ViewHolder(binding.root) {

    fun onBind(chatListItem: ChatListItem) {
        with(binding) {
            sivProfile.setImageURI(Uri.parse(chatListItem.imageUrl))
            tvName.text = chatListItem.name
            tvLastMsg.text = chatListItem.lastMessage
            tvLastMsgTime.text = chatListItem.lastMessageTimeStamp.toString() // TODO: parse time since epoch
            tvNumberBadge.text = chatListItem.unreadMessagesCount.toString()
        }
    }
}