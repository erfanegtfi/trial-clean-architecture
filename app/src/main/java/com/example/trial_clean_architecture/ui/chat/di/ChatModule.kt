package com.example.trial_clean_architecture.ui.chat.di

import com.example.common.CHAT_SOCKET_RUL
import dagger.Module
import dagger.Provides
import io.socket.client.IO
import io.socket.client.Socket
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Named
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Module
object ChatModule {


    @Provides
    @ChatScope
    @Named("chatOkHttpClient")
    fun provideOkHttpClient(okHttpClient: OkHttpClient): OkHttpClient {

        val hostnameVerifier = HostnameVerifier { hostname, session -> true }
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<X509Certificate?>?,
                authType: String?
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<X509Certificate?>?,
                authType: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                return arrayOfNulls(0)
            }
        })

        val trustManager: X509TrustManager = trustAllCerts[0] as X509TrustManager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()


        return okHttpClient.newBuilder().hostnameVerifier(hostnameVerifier)
            .sslSocketFactory(sslSocketFactory, trustManager)
            .build()
    }

    @Provides
    @ChatScope
    fun provideSocketIO(@Named("chatOkHttpClient") okHttpClient: OkHttpClient): Socket {

        return IO.socket(CHAT_SOCKET_RUL,
            IO.Options().apply {
                query =
                    "token="
                secure = true
                reconnection = true
                callFactory = okHttpClient
                webSocketFactory = okHttpClient
                reconnectionAttempts = Int.MAX_VALUE
//                reconnectionDelay = SOCKET_RECONNECTION_DELAY
//                timeout = SOCKET_TIMEOUT
                randomizationFactor = 0.5
            }
        )
    }
}