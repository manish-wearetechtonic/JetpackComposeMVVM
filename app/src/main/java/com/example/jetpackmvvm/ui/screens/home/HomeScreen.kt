package com.example.jetpackmvvm.ui.screens.home


import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    productViewModel: ProductViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
 ) {
    productViewModel.getProductList()
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val productList = productViewModel.products.collectAsState().value
        val isLoading = productViewModel.isLoading.collectAsState().value
        val context = LocalContext.current

        LaunchedEffect(key1 = productViewModel.showErrorToastChannel) {
            productViewModel.showErrorToastChannel.collectLatest { show->
                if(show){
                    Toast.makeText(context,"Error while getting products",Toast.LENGTH_SHORT).show()
                }
            }
        }
        
       if(isLoading){
           Box(
               modifier = Modifier.fillMaxSize(),
               contentAlignment = Alignment.Center
           ){
               CircularProgressIndicator()
           }
       }else if (productList.isEmpty()) {
           Box(
               modifier = Modifier.fillMaxSize(),
               contentAlignment = Alignment.Center
           ) {
               Text(text = "No data found")
           }
       }else{
           LazyColumn {
               items ( productList.size){ index->
                   val product = productList[index]
                   Text(text = product.title)
               }
           }
       }
        
    }
}




