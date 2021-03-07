package com.example.trial_clean_architecture.ui.transaction.di

import com.example.trial_clean_architecture.MainActivity
import com.example.trial_clean_architecture.contact.ContactFragment
import com.example.trial_clean_architecture.di.component.AppComponent
import com.example.trial_clean_architecture.setting.SettingFragment
import com.example.trial_clean_architecture.ui.transaction.TransactionFragment
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@TransactionScope
@Component(dependencies = [AppComponent::class], modules = [TransactionModule::class, ViewModeTransactionModule::class])
interface TransactionComponent {

    fun inject(transactionFragment: TransactionFragment)
    fun inject(contactFragment: ContactFragment)
    fun inject(settingFragment: SettingFragment)
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): TransactionComponent
    }
}