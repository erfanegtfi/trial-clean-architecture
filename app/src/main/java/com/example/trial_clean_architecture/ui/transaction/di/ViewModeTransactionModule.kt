package com.example.trial_clean_architecture.ui.transaction.di

import androidx.lifecycle.ViewModel
import com.example.trial_clean_architecture.di.ViewModelKey
import com.example.trial_clean_architecture.ui.transaction.TransactionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Suppress("unused")
@Module
//internal
abstract class ViewModeTransactionModule {

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    abstract fun bindTrackViewModel(transactionViewModel: TransactionViewModel): ViewModel

}