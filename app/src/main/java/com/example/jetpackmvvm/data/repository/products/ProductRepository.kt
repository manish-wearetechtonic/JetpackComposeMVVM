package com.example.jetpackmvvm.data.repository.products

import com.example.jetpackmvvm.data.datasourc.remote.ApiServices
import com.example.jetpackmvvm.data.model.Product
import com.example.jetpackmvvm.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private  val apiServices: ApiServices
): ProductRepositoryInterface {

    override suspend fun getProductList(): Flow<DataState<List<Product>>> = flow {
        emit(DataState.Loading)
        try {
            val products = apiServices.getProductsList()
            emit(DataState.Success(products.products))

        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    override suspend fun getProductById(productId: Int): Flow<DataState<Product>> = flow {
        emit(DataState.Loading)

        try {
            val product = apiServices.getProductById(productId)
            emit(DataState.Success(product))

        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

}