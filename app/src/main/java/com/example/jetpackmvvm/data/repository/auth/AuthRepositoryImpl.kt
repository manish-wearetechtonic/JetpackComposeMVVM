package com.example.jetpackmvvm.data.repository.auth

import android.annotation.SuppressLint
import com.example.jetpackmvvm.data.datasourc.remote.AuthApi
import com.example.jetpackmvvm.data.datasourc.remote.LoginRequestBody
import com.example.jetpackmvvm.data.preference.PreferenceHelper
import com.example.jetpackmvvm.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

    private val preferenceHelper: PreferenceHelper,
    private val api: AuthApi
) : AuthRepository {

    @SuppressLint("SuspiciousIndentation")
    override suspend fun login(username: String, password: String): Flow<DataState<Boolean>> =
        flow {
            emit(DataState.Loading)
            try {
                val requestBody = LoginRequestBody(username, password)
                val authData = api.authApi(requestBody)

                        preferenceHelper.saveAccessToken(authData.token)
                        preferenceHelper.saveLoggedInUserId(authData.id.toString())
                        preferenceHelper.saveLoggedInStatus(true)
                        emit(DataState.Success(true))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }




    override fun isAuthenticated(): Boolean {
        return preferenceHelper.getLoggedInStatus()
    }
}
