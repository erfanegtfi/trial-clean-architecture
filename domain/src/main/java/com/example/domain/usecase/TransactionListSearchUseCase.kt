package com.example.domain.usecase

import com.example.common.utils.ResponseResult
import com.example.domain.model.TransactionsDomainModel
import com.example.domain.repository.TransactionRepository
import com.example.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionListSearchUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) ://    private val dispatcher: CoroutineDispatcher

BaseUseCase<ResponseResult<List<TransactionsDomainModel>>, String?>() {


    override suspend fun run(params: String?): Flow<ResponseResult<List<TransactionsDomainModel>>> {
        return transactionRepository.getTransactionList(params)
    }

}