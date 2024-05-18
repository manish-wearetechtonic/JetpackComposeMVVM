package com.example.jetpackmvvm.ui.screens.home.productdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.jetpackmvvm.data.model.Product
import com.example.jetpackmvvm.ui.navigation.topappbar.ChildAppTopBar
import com.example.jetpackmvvm.ui.theme.appTypography

@Composable
fun ProductDetailScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    productId: String?,
    onBackPressed: () -> Unit,
) {
    val productState by viewModel.productState.collectAsState()

    LaunchedEffect(Unit){
        if (productId != null){
            viewModel.getProductById(productId.toInt())
        }
    }

    if (productState.productEntity != null) {
        ProductContent(productState.productEntity!!) {
            onBackPressed()
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductContent(
    productEntity: Product, onBackPressed: () -> Unit
) {
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        topBar = {
            ChildAppTopBar(
                toolbarTitle = productEntity.title,
                barScrollBehavior = barScrollBehavior,
                onBackPressed
            )
        }, modifier = Modifier
            .fillMaxSize()
            .nestedScroll(barScrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column {
                AsyncImage(
                    model = productEntity.thumbnail,
                    contentDescription = productEntity.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
                )

                Column(
                    Modifier
                        .padding(10.dp),
                ) {
                    Text(
                        text = productEntity.category.uppercase(),
                        style = appTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = productEntity.title,
                        style = appTypography.titleLarge,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = productEntity.description,
                        style = appTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                }


            }
        }

    }

}





