package com.example.jetpackmvvm.di

import com.example.jetpackmvvm.data.datasourc.remote.ApiServices
import com.example.jetpackmvvm.data.datasourc.remote.AuthApi
import com.example.jetpackmvvm.data.preference.PreferenceHelper
import com.example.jetpackmvvm.data.repository.auth.AuthRepository
import com.example.jetpackmvvm.data.repository.auth.AuthRepositoryImpl
import com.example.jetpackmvvm.data.repository.products.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(apiServices: ApiServices): ProductRepository {
        return ProductRepository(apiServices)
    }


    @Singleton
    @Provides
    fun provideAuthRepository(authApi: AuthApi, preferenceHelper: PreferenceHelper): AuthRepository {
        return AuthRepositoryImpl(preferenceHelper, authApi)
    }


}