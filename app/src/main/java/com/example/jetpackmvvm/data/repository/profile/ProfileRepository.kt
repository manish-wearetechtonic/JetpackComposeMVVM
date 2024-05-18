package com.example.jetpackmvvm.data.repository.profile

import com.example.jetpackmvvm.data.datasourc.remote.ApiServices
import com.example.jetpackmvvm.data.model.profile.UserProfile
import com.example.jetpackmvvm.data.preference.PreferenceHelper
import com.example.jetpackmvvm.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val preferenceHelper: PreferenceHelper,
) : ProfileRepositoryInterface {
    override suspend fun getUserProfile(userId: Int): Flow<DataState<UserProfile>> = flow {

        emit(DataState.Loading)

        try {

            val userProfile = apiServices.getUserProfile(userId)
            emit(DataState.Success(userProfile))

        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    override suspend fun logout(): Flow<DataState<Boolean>> = flow {
        preferenceHelper.clearPreference()
        emit(DataState.Success(true))
    }


}