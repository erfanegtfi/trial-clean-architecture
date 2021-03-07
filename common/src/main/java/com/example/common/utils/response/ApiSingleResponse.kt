package com.example.common.utils.response;


import com.google.gson.annotations.SerializedName;


class ApiSingleResponse<T>(
    @SerializedName("response_value") var data: T
) : ApiBaseResponse() {


}
