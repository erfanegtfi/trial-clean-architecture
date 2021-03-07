package com.example.trial_clean_architecture.ui.chat.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_clean_architecture.databinding.ItemChatPartnerMessageBinding
import com.example.trial_clean_architecture.model.ChatContent

class ChatPartnerMessageItemViewHolder(private val binding: ItemChatPartnerMessageBinding, private val context: Context) : RecyclerView.ViewHolder(
    binding.root
) {
    fun bind(model: ChatContent) {
        binding.message = model

    }
}