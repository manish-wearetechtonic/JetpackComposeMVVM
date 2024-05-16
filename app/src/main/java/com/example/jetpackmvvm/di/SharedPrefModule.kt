package com.example.jetpackmvvm.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKey.*
import com.example.jetpackmvvm.data.preference.PrefConstants
import com.example.jetpackmvvm.data.preference.PreferenceHelper
import com.example.jetpackmvvm.data.preference.PreferenceHelperImpl
import com.example.jetpackmvvm.di.qualifiers.SharedPrefInfoQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.IOException
import java.security.GeneralSecurityException
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {


    @Provides
    @SharedPrefInfoQualifier
    fun provideSharedPreferenceName(): String {
        return PrefConstants.PREF_FILE_NAME
    }

//    @Provides
//    fun provideNormalSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
//        PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideSecureSharedPreferences(
        @ApplicationContext context: Context,
        @SharedPrefInfoQualifier fileName: String
    ): SharedPreferences {
        try {
            val masterKeyAlias = Builder(context)
                .setKeyScheme(KeyScheme.AES256_GCM)
                .build()

            return EncryptedSharedPreferences.create(
                context,
                fileName,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: GeneralSecurityException) {
            // Handle cryptographic exception
            e.printStackTrace()
        } catch (e: IOException) {
            // Handle IO exception
            e.printStackTrace()
        }

        // If an exception occurs, fallback to using regular SharedPreferences
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(sharedPrefHelper: PreferenceHelperImpl): PreferenceHelper {
        return sharedPrefHelper
    }
}