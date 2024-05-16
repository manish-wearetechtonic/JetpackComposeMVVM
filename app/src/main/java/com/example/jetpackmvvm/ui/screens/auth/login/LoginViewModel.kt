package com.example.jetpackmvvm.ui.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackmvvm.data.model.Product
import com.example.jetpackmvvm.data.repository.auth.AuthRepository
import com.example.jetpackmvvm.data.repository.products.ProductRepository
import com.example.jetpackmvvm.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun login(username:String, password: String) {
        viewModelScope.launch {
            try {
                authRepository.login(username, password).collectLatest { result ->
                    when (result) {
                        is DataState.Error -> {
                            Log.d("error@@@",result.exception.toString())
                        }
                        DataState.Loading -> {

                        }
                        is DataState.Success -> {
                            result.data.let { loginResponse ->
                              Log.d("Success Result",loginResponse.toString())
                            }
                        }

                    }
                }
            }catch (e: Exception){
                Log.d("Exception",e.localizedMessage)
            }
        }

    }
}
