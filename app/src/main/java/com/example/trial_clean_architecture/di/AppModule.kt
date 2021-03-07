package com.example.trial_clean_architecture.di


import androidx.annotation.NonNull
import com.example.common.BASE_URL
import com.example.common.REQUEST_TIMEOUT_DURATION
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.common.utils.NetInterceptorApiLogger
import com.example.data.dataSource.remote.api.ApiInterface
import com.example.data.repository.TransactionRepositoryImpl
import com.example.data.utils.NetInterceptorApiHeader
import com.example.domain.repository.TransactionRepository
import com.example.trial_clean_architecture.BuildConfig
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@ExperimentalCoroutinesApi
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(NetInterceptorApiLogger())
//                .addInterceptor(WatchTowerInterceptor())
                .addInterceptor(NetInterceptorApiHeader())
                .connectTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(NetInterceptorApiHeader())
                .connectTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                .build()
        }

    }


    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create()
    }

    @Provides
    fun provideProductRepository(productRepository: TransactionRepositoryImpl): TransactionRepository {
        return productRepository
    }


    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient, gson: Gson): Retrofit {

        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .addConverterFactory(ScalarsConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

}