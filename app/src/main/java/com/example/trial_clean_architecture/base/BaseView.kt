package com.example.trial_clean_architecture.base;

import com.example.common.utils.GeneralError
import com.example.common.utils.response.ApiBaseResponse


interface BaseView {

    fun showLoading(message: String?)

    fun showLoading()

    fun hideLoading()

    fun unauthorizedUser(response: ApiBaseResponse?)

    fun onTimeout(throwable: Throwable?)

    fun onNetworkError(throwable: Throwable?)

    fun onError(error: GeneralError?)

    fun onResponseMessage(message: ApiBaseResponse?)

}