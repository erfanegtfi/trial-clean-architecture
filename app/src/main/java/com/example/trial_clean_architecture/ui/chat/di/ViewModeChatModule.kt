package com.example.trial_clean_architecture.ui.chat.di

import androidx.lifecycle.ViewModel
import com.example.trial_clean_architecture.di.ViewModelKey
import com.example.trial_clean_architecture.ui.chat.ChatViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Suppress("unused")
@Module
//internal
abstract class ViewModeChatModule {

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindTrackViewModel(chatViewModel: ChatViewModel): ViewModel

}