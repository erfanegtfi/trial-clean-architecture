package com.example.trial_clean_architecture.base;


import androidx.lifecycle.ViewModel
import com.example.common.utils.ResponseResult
import com.example.common.utils.response.ApiBaseResponse
import com.example.trial_clean_architecture.utils.SingleLiveEvent


open class BaseViewModel() : ViewModel() {

//    var listLoadingState: ObservableField<ListLoadState> =  ObservableField(ListInitial)
    var apiEvents: SingleLiveEvent<ResponseResult<ApiBaseResponse>> = SingleLiveEvent()

    //    var apiEvents2: SingleLiveEvent<ResponseResultWithWrapper<ResponseWrapper<ApiBaseResponse>>> = SingleLiveEvent()
//    var compositeDisposable: CompositeDisposable = CompositeDisposable()


    open fun onDestroy() {
//        compositeDisposable.clear()
    }

}
