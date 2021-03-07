package com.example.trial_clean_architecture.ui.chat.di

import com.example.trial_clean_architecture.di.component.AppComponent
import com.example.trial_clean_architecture.ui.chat.ChatActivity
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ChatScope
@Component(dependencies = [AppComponent::class], modules = [ChatModule::class, ViewModeChatModule::class])
interface ChatComponent {

    fun inject(chatActivity: ChatActivity)

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): ChatComponent
    }
}