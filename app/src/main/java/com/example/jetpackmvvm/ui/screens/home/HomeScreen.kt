package com.example.jetpackmvvm.ui.screens.home


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.jetpackmvvm.ui.theme.dimens
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
) {
    homeViewModel.getProductList()


    val productList = homeViewModel.products.collectAsState().value
    val isLoading = homeViewModel.isLoading.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(key1 = homeViewModel.showErrorToastChannel) {
        homeViewModel.showErrorToastChannel.collectLatest { show ->
            if (show) {
                Toast.makeText(context, "Error while getting products", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (productList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No data found")
            }
        } else {
            LazyColumn {
                items(productList.size) { index ->
                    val product = productList[index]
                    Card(
                        modifier = Modifier
                            .padding(MaterialTheme.dimens.regular)
                            .shadow(
                                elevation = 5.dp,
                                spotColor = MaterialTheme.colorScheme.secondaryContainer,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clickable {
                                onProductClick(product.id.toString())
                            },
                        shape = MaterialTheme.shapes.medium

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = product.thumbnail,
                                contentDescription = product.title,
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.secondaryContainer)
                                    .fillMaxWidth()
                                    .height(150.dp),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
    }

}








