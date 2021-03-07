package com.example.trial_clean_architecture.model.listTile.chat

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_clean_architecture.R
import com.example.trial_clean_architecture.databinding.ItemChatPartnerMessageBinding
import com.example.trial_clean_architecture.model.ChatContent
import com.example.trial_clean_architecture.ui.chat.adapter.ChatPartnerMessageItemViewHolder
import com.example.trial_clean_architecture.ui.common.ListItem
import com.example.trial_clean_architecture.ui.common.Tile

object ChatItemPartnerMessageTile : Tile<ListItem>() {
    override fun isTypeOf(item: ListItem?): Boolean {
        var isPartnerMessageTile = false
        if(item is ChatContent){
            isPartnerMessageTile = item is ChatMessage.PartnerMessage
        }

        return isPartnerMessageTile
    }

    override fun type(): Int {
        return R.layout.item_chat_partner_message
    }

    override fun holder(
        parent: ViewGroup,
        context: Context,
        updateAdapter: () -> Unit
    ): RecyclerView.ViewHolder {
        return ChatPartnerMessageItemViewHolder(parent.viewOf(type()) as ItemChatPartnerMessageBinding, context)
    }

    override fun bind(holder: RecyclerView.ViewHolder, item: ListItem?) {
        if (holder is ChatPartnerMessageItemViewHolder && item is ChatMessage.PartnerMessage) {
            holder.bind(item.message)
        }
    }
}