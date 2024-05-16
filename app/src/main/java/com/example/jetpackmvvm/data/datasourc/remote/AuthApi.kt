package com.example.jetpackmvvm.data.datasourc.remote

import com.example.jetpackmvvm.data.model.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

data class LoginRequestBody(
    val username: String,
    val password: String,
    val expiresInMins: Int = 30 // optional, defaults to 30
)

interface AuthApi {

//    @FormUrlEncoded
//    @POST("auth/login")
//    fun authApi(
//        @Field("username") username: String,
//        @Field("password") password: String
//    ): Flow<UserResponse>

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun authApi(@Body requestBody: LoginRequestBody): UserResponse
}