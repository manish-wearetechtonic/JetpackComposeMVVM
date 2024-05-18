package com.example.jetpackmvvm.data.repository.profile

import com.example.jetpackmvvm.data.model.profile.UserProfile
import com.example.jetpackmvvm.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface ProfileRepositoryInterface {

    suspend fun getUserProfile(userId: Int) : Flow<DataState<UserProfile>>
    suspend fun logout(): Flow<DataState<Boolean>>
}