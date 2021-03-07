package com.example.trial_clean_architecture.ui.transaction;

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.utils.ResponseResult
import com.example.domain.model.TransactionsDomainModel
import com.example.domain.usecase.TransactionListSearchUseCase
import com.example.domain.usecase.TransactionListUseCase
import com.example.trial_clean_architecture.base.BaseViewModel
import com.example.trial_clean_architecture.mapper.TransactionPresentationToDomainMapper
import com.example.trial_clean_architecture.model.Transactions
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TransactionViewModel
@Inject constructor(
    private val transactionListUseCase: TransactionListUseCase,
    private val transactionListSearchUseCase: TransactionListSearchUseCase
) : BaseViewModel() {

    var loading: ObservableField<Boolean> = ObservableField(false)
    var error: ObservableField<Boolean> = ObservableField(false)

    private val _transactionLiveData = MutableLiveData<ResponseResult<List<Transactions>>>()
    val transactionListLiveData: LiveData<ResponseResult<List<Transactions>>>
        get() = _transactionLiveData


    fun getTransactionList() {
        viewModelScope.launch {
            transactionListUseCase.invoke("").onStart {
                _transactionLiveData.value = ResponseResult.Loading
                loading.set(true)
            }.collect { result ->

                handelTransactionResponse(result)
            }
        }
    }

//    @FlowPreview
//    fun setSearchQuery(query: String) {
//        viewModelScope.launch {
//
//            transactionListUseCase.invoke(query).onStart {
//                _transactionLiveData.value = ResponseResult.Loading
//            }.debounce(700)
//                .collect { result ->
//                    handelTransactionResponse(result)
//                }
//        }
//    }

    private fun handelTransactionResponse(result: ResponseResult<List<TransactionsDomainModel>>) {
        when (result) {
            is ResponseResult.Success -> {
                if (result.response.isNotEmpty())
                    loading.set(false)
                error.set(false)
                _transactionLiveData.value =
                    ResponseResult.Success(response = result.response.map {
                        TransactionPresentationToDomainMapper.toPresenterModel(it)
                    })
            }
            is ResponseResult.Error -> {
                error.set(true)
                loading.set(false)
                _transactionLiveData.value = ResponseResult.Error(error = result.error)
            }

            else -> {
            }
        }
    }

    //    @FlowPreview
//    fun getTransactionList2() {
//        viewModelScope.launch {
//
//            searchChanel.asFlow().debounce(700)
//                .flatMapLatest { query ->
//                    transactionListUseCase.run(query).onStart {
//                        _transactionLiveData.value = ResponseResult.Loading
//                    }
//                }.catch { throwable ->
//                    throwable.printStackTrace()
//                }.collect { result ->
//                    handelTransactionResponse(result)
//                }
//        }
//    }

    @ExperimentalCoroutinesApi
    private val searchChanel = ConflatedBroadcastChannel<String>()
//
    @FlowPreview
    @ExperimentalCoroutinesApi
    val searchPostListLiveData = searchChanel.asFlow().debounce(700)
        .flatMapLatest { query ->

//            listLoadingState.set(ListLoadState.ListLoaded)
            transactionListSearchUseCase.invoke(query).onStart {
                ResponseResult.Loading
            }
//        }.map{result->
//            ResponseResult.Success(response = result.map {
//                TransactionPresentationToDomainMapper.toPresenterModel(it)
//            })
        }.onEach {
        handelTransactionResponse(it)
//            if (it.response.isEmpty())
//                error.set(true)
//            else
//                error.set(false)
        }
        .catch { throwable ->
            throwable.printStackTrace()
        }.launchIn(viewModelScope)


    fun setSearchQuery(search: String) {
        searchChanel.offer(search)
    }

    fun onRefresh() {
        getTransactionList()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelScope.cancel()
    }


}