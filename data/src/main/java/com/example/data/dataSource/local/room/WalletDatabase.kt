package com.example.data.dataSource.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dataSource.local.room.dao.TransactionDao
import com.example.data.models.TransactionsEntity

@Database(entities = [TransactionsEntity::class], version = DatabaseMigrations.DB_VERSION)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun getProductDao(): TransactionDao

    companion object {
        const val DB_NAME = "wallet_database"

        @Volatile
        private var INSTANCE: WalletDatabase? = null

        fun getInstance(context: Context): WalletDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, WalletDatabase::class.java, DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }

    }
}