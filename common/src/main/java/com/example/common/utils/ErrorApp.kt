package com.example.common.utils
import com.google.gson.annotations.SerializedName


data class ErrorApp(
    @SerializedName("category")
    val category: String?,
    @SerializedName("message")
    val message: String?
)
