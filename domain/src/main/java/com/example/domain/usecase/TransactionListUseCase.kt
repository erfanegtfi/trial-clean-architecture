package com.example.domain.usecase

import com.example.common.utils.ResponseResult
import com.example.domain.model.TransactionsDomainModel
import com.example.domain.repository.TransactionRepository
import com.example.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionListUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) ://    private val dispatcher: CoroutineDispatcher

BaseUseCase<ResponseResult<List<TransactionsDomainModel>>, Any?>() {


    override suspend fun run(params: Any?): Flow<ResponseResult<List<TransactionsDomainModel>>> {
        return transactionRepository.getTransactionList()
    }

//    suspend fun getTransactionList(): Flow<ResponseResult<List<TransactionsDomainModel>>> {
//        return transactionRepository.getTransactionList()
//    }
//
//    suspend fun getTransactionList(query: String): Flow<List<TransactionsDomainModel>> {
//        return transactionRepository.getTransactionList(query)
//    }
}