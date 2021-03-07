package com.example.data.dataSource.local.room.dao

import androidx.room.*
import com.example.data.models.TransactionsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(posts: List<TransactionsEntity>)

    @Query("SELECT * FROM ${TransactionsEntity.TABLE_NAME}")
    fun getAllTransactions(): Flow<List<TransactionsEntity>>

    @Query("SELECT * FROM ${TransactionsEntity.TABLE_NAME} Where firstName LIKE '%' || :query || '%' or lastName LIKE '%' || :query || '%'")
    fun getAllTransactions(query: String?): Flow<List<TransactionsEntity>>

}