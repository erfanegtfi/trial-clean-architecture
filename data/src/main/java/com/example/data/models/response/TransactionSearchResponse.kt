package com.example.data.models.response;

import com.example.common.utils.response.ApiBaseResponse
import com.example.data.models.TransactionsEntity
import com.google.gson.annotations.SerializedName
import java.util.*

data class TransactionsResponse (
    @SerializedName("receipt_uuid")
    val receiptUUID: String?,
    @SerializedName("response_code")
    val responseCode: Int?,
    @SerializedName("transactions")
    var transactions:  List<TransactionsEntity> = ArrayList()

): ApiBaseResponse()