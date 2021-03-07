package com.example.trial_clean_architecture.di.component

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.example.trial_clean_architecture.di.AppModule
import com.example.trial_clean_architecture.di.PersistenceModule
import com.example.trial_clean_architecture.di.viewModel.ViewModelFactoryModule
import com.example.trial_clean_architecture.di.viewModel.ViewModelModule
import com.example.data.dataSource.remote.api.ApiInterface
import com.example.data.dataSource.local.room.WalletDatabase
import com.example.data.dataSource.local.room.dao.TransactionDao
import com.example.domain.repository.TransactionRepository
import com.example.trial_clean_architecture.App
import com.example.trial_clean_architecture.MainActivity
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(
    modules = [AppModule::class, ViewModelFactoryModule::class, ViewModelModule::class, PersistenceModule::class] //
)
interface AppComponent {
    fun inject(app: App)
    fun inject(mainActivity: MainActivity)

    fun provideApiService(): ApiInterface
    fun provideRetrofit(): Retrofit
    fun provideOkHttpClient(): OkHttpClient
    fun provideGson(): Gson

    fun provideBookDao(): TransactionDao
    fun provideDatabase(): WalletDatabase
    fun bindViewModelFactory(): ViewModelProvider.Factory
    fun provideProductRepository(): TransactionRepository
    fun applicationContext(): Application
//    fun context(): Context


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}