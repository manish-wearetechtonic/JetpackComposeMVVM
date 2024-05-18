package com.example.jetpackmvvm.data.repository.products

import Products
import com.example.jetpackmvvm.data.model.Product
import com.example.jetpackmvvm.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface ProductRepositoryInterface {

    suspend fun getProductList() : Flow<DataState<List<Product>>>

    suspend fun getProductById(productId: Int) : Flow<DataState<Product>>

}