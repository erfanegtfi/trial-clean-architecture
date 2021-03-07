package com.example.data.repository


import com.example.common.utils.GeneralError
import com.example.data.repository.base.LocalBoundRepository
import com.example.data.mapper.TransactionEntityToDomainModelMapper
import com.example.common.utils.ResponseResult
import com.example.data.dataSource.local.room.TransactionLocalDataSource
import com.example.data.dataSource.remote.TransactionRemoteDataSource
import com.example.data.models.response.TransactionsResponse
import com.example.domain.model.TransactionsDomainModel
import com.example.domain.repository.TransactionRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TransactionRepositoryImpl @Inject constructor(
    private val transactionLocalDataSource: TransactionLocalDataSource,
    private val transactionRemoteDataSource: TransactionRemoteDataSource
) : TransactionRepository {

    // fetch data from remote and save in database
    override suspend fun getTransactionList(): Flow<ResponseResult<List<TransactionsDomainModel>>> {
        return object : LocalBoundRepository<List<TransactionsDomainModel>, TransactionsResponse>() {

            override suspend fun saveRemoteData(response: TransactionsResponse) =
                transactionLocalDataSource.saveTransactionList(response.transactions)

            override fun fetchFromLocal(): Flow<List<TransactionsDomainModel>> =
                transactionLocalDataSource.fetchTransactionList("").map { list ->
                    list.map { entity ->
                        TransactionEntityToDomainModelMapper.toDomainModel(entity)
                    }
                }

            override suspend fun fetchFromRemote(): TransactionsResponse = transactionRemoteDataSource.getTransactionList()

        }.getFlow().flowOn(Dispatchers.IO)

    }

    //    //offline search
    override suspend fun getTransactionList(query: String?): Flow<ResponseResult<List<TransactionsDomainModel>>> =

        transactionLocalDataSource.fetchTransactionList(query).map { list ->
            list.map { entity ->
                TransactionEntityToDomainModelMapper.toDomainModel(entity)
            }
        }.map {
            if (it.isEmpty())
                ResponseResult.Error(error = GeneralError())
            else
                ResponseResult.Success(response = it)
        }
}