package com.example.jetpackmvvm.ui.screens.home.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackmvvm.data.model.Product
import com.example.jetpackmvvm.data.repository.products.ProductRepository
import com.example.jetpackmvvm.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository ) : ViewModel() {
    private val _productState = MutableStateFlow(ProductState(null))
    val productState = _productState.asStateFlow()

    fun getProductById(productId: Int) {

        viewModelScope.launch {
            productRepository.getProductById(productId).collectLatest { productResource ->

                when (productResource) {
                    is  DataState.Loading -> {}

                    is DataState.Success -> {
                        _productState.value =ProductState( productResource.data)
                    }

                    else -> {

                    }
                }

            }

        }
    }
}