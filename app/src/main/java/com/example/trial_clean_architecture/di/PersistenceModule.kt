package com.example.trial_clean_architecture.di

import android.app.Application
import androidx.annotation.NonNull
import com.example.data.dataSource.local.room.WalletDatabase
import com.example.data.dataSource.local.room.dao.TransactionDao
import dagger.Module
import dagger.Provides

@Module
object PersistenceModule {

//
    @Provides
    fun provideBookDao(@NonNull database: WalletDatabase): TransactionDao {
        return database.getProductDao()
    }

    @Provides
    fun provideDatabase(@NonNull application: Application): WalletDatabase {
        return WalletDatabase.getInstance(application)
    }


}