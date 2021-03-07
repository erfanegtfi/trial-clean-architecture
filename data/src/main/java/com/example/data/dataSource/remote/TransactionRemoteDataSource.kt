package com.example.data.dataSource.remote

import com.example.common.utils.getResponse
import com.example.data.dataSource.remote.api.ApiInterface
import com.example.data.models.response.TransactionsResponse
import javax.inject.Inject

class TransactionRemoteDataSource  @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getTransactionList(): TransactionsResponse =
        apiInterface.getTransaction().getResponse()

}