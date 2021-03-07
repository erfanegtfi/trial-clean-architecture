package com.example.common.utils.response;

import com.example.common.utils.ErrorApp
import com.google.gson.annotations.SerializedName;

open class ApiBaseResponse(
    @SerializedName("errors") var errors: List<ErrorApp>? = null,
//    @SerializedName("message") var msg: String? = null,
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("http_code") var httpCode: Int? = null,
) {
}
