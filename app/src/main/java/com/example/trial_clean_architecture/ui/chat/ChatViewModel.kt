package com.example.trial_clean_architecture.ui.chat;

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.utils.ResponseResult
import com.example.domain.usecase.TransactionListUseCase
import com.example.trial_clean_architecture.base.BaseViewModel
import com.example.trial_clean_architecture.model.Transactions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ChatViewModel
@Inject constructor(
    private val transactionListUseCase: TransactionListUseCase
) : BaseViewModel() {

    var loading: ObservableField<Boolean> = ObservableField(false)
    var error: ObservableField<Boolean> = ObservableField(false)

    private val _transactionLiveData = MutableLiveData<ResponseResult<List<Transactions>>>()
    val transactionListLiveData: LiveData<ResponseResult<List<Transactions>>>
        get() = _transactionLiveData

    override fun onDestroy() {
        super.onDestroy()
        viewModelScope.cancel()
    }


}