package com.example.data.dataSource.remote.api;

import com.example.data.models.response.TransactionsResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {

    @GET("billing/transactions")
    suspend fun getTransaction(): Response<TransactionsResponse>

}
