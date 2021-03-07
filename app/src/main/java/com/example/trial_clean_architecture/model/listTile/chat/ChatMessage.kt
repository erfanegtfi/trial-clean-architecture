package com.example.trial_clean_architecture.model.listTile.chat

import com.example.trial_clean_architecture.model.ChatContent
import com.example.trial_clean_architecture.ui.common.ListItem

sealed class ChatMessage : ListItem{
    data class MyMessage(val message: ChatContent) : ChatMessage(){
        override val listID: String = message.listID
    }
    data class PartnerMessage(val message: ChatContent) : ChatMessage(){
        override val listID: String = message.listID
    }
}