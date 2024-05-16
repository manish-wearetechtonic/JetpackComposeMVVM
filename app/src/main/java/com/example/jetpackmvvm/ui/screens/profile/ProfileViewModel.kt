package com.example.jetpackmvvm.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackmvvm.data.repository.auth.AuthRepository
import com.example.jetpackmvvm.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor( private val authRepository: AuthRepository) : ViewModel(){

    fun logout(){
    viewModelScope.launch {
        try {
            authRepository.logout().collectLatest {
                when(it){
                    is DataState.Error -> {
                        Log.d("error@@@",it.exception.toString())
                    }
                    DataState.Loading -> {

                    }
                    is DataState.Success -> {
                        it.data.let { logoutRes ->
                            Log.d("Success Result",logoutRes.toString())
                        }
                    }
                }
            }

        }catch (e: Exception){
            Log.d("Error On Logout",e.localizedMessage)
        }

    }
}
}