package com.example.domain.usecase.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<Type, in Params>() where Type : Any {//private val dispatcher: CoroutineDispatcher

    abstract suspend fun run(params: Params? = null): Flow<Type>

    suspend fun invoke(params: Params? = null): Flow<Type> {

       return  run(params).flowOn(Dispatchers.IO)
    }
}