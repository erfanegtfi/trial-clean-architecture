package com.example.data.dataSource.local.room

import com.example.data.dataSource.local.room.dao.TransactionDao
import com.example.data.mapper.TransactionEntityToDomainModelMapper
import com.example.data.models.TransactionsEntity
import com.example.domain.model.TransactionsDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionLocalDataSource @Inject constructor(
    private val postsDao: TransactionDao,
) {

    fun saveTransactionList(transactions: List<TransactionsEntity>) =
        postsDao.insertTransaction(transactions)

    fun fetchTransactionList(query: String?): Flow<List<TransactionsEntity>> =
        postsDao.getAllTransactions(query)
}