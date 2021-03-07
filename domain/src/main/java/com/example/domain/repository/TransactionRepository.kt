package com.example.domain.repository

import com.example.common.utils.ResponseResult
import com.example.domain.model.TransactionsDomainModel
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun getTransactionList(): Flow<ResponseResult<List<TransactionsDomainModel>>>
    suspend fun getTransactionList(query: String?): Flow<ResponseResult<List<TransactionsDomainModel>>>

//    suspend fun getTransactionList(query: String?): Flow<ResponseResult<List<TransactionsDomainModel>>>
}