package com.example.data.utils


import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetInterceptorApiHeader() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
//        val network: Boolean = Utils.isConnectingToInternet(mContext)
//        if (!network) {
//            throw NoConnectivityException()
//        }
//        val token: String = Session.getPreferenceValue(Session.TAG_USER_TOKEN, null)
        val chainn = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .header("Content-Type", "application/json")
//        if (!TextUtils.isEmpty(token)) chainn.header("Authorization", "JWT $token")
        chainn.header(
            "Authorization",
            "Bearer " + ""
        )
        val request = chainn.build()
        return chain.proceed(request)
    }

}