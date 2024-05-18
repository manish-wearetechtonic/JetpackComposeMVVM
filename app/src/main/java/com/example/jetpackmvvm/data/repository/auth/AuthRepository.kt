package com.example.jetpackmvvm.data.repository.auth

import com.example.jetpackmvvm.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Flow<DataState<Boolean>>

    fun isAuthenticated(): Boolean
}
