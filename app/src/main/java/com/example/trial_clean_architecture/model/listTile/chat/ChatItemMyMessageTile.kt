package com.example.trial_clean_architecture.model.listTile.chat

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_clean_architecture.R
import com.example.trial_clean_architecture.databinding.ItemChatMyMessageBinding
import com.example.trial_clean_architecture.ui.chat.adapter.ChatMyMessageItemViewHolder
import com.example.trial_clean_architecture.ui.common.ListItem
import com.example.trial_clean_architecture.ui.common.Tile
//{"tran_id":16,"chat_id":496,"user_id":4,"status":0,"message":"Hi Im Erfan ...","update_time":20210122124318}
object ChatItemMyMessageTile : Tile<ListItem>() {
    override fun isTypeOf(item: ListItem?): Boolean {
        var isMyMessageTile = false
        if(item is ChatMessage){
            isMyMessageTile = item is ChatMessage.MyMessage
        }

        return isMyMessageTile
    }

    override fun type(): Int {
        return R.layout.item_chat_my_message
    }

    override fun holder(
        parent: ViewGroup,
        context: Context,
        updateAdapter: () -> Unit
    ): RecyclerView.ViewHolder {
        return ChatMyMessageItemViewHolder(parent.viewOf(type()) as ItemChatMyMessageBinding, context)
    }

    override fun bind(holder: RecyclerView.ViewHolder, item: ListItem?) {
        if (holder is ChatMyMessageItemViewHolder && item is ChatMessage.MyMessage) {
            holder.bind(item.message)
        }
    }
}