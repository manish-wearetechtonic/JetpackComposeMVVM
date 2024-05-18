package com.example.jetpackmvvm.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackmvvm.data.model.Product
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
class HomeViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val  products = _products.asStateFlow()

    private  val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    fun getProductList() {
        viewModelScope.launch {
            _isLoading.value = true
                try {
                    repository.getProductList().collectLatest { result ->
                        when (result) {
                            is DataState.Error -> {
                                Log.d("error@@@",result.exception.toString())
                                _showErrorToastChannel.send(true)
                            }
                            DataState.Loading -> {

                            }
                            is DataState.Success -> {
                                result.data.let { products ->
                                    _products.update { products }
                                }
                            }

                        }
                        _isLoading.value = false
                }
            }catch (e: Exception){
                    _isLoading.value = true
                }
        }

    }
}
