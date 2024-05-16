package com.example.jetpackmvvm.ui.screens.profile


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackmvvm.ui.screens.auth.login.LoginViewModel

@Composable
fun ProfileScreen(
    loginViewModel: ProfileViewModel = hiltViewModel(),
    onLogout: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                loginViewModel.logout()
                onLogout()
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Logout")
        }
    }
}