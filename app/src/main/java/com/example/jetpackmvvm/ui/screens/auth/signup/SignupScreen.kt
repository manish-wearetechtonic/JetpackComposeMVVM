package com.example.jetpackmvvm.ui.screens.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun SignupScreen(
    onNavigateBack: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    val emailErrorState = remember { mutableStateOf<String?>(null) }
    val passwordErrorState = remember { mutableStateOf<String?>(null) }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Register here!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                    emailErrorState.value = if (it.isEmpty()) "Email cannot be empty" else null
                },
                label = { Text("Email") },
                isError = emailErrorState.value != null,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = MaterialTheme.shapes.medium
            )
            emailErrorState.value?.let { errorMessage ->
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            OutlinedTextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                    passwordErrorState.value = if (it.isEmpty()) "Password cannot be empty" else null
                },
                label = { Text("Password") },
                isError = passwordErrorState.value != null,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = MaterialTheme.shapes.medium
            )
            passwordErrorState.value?.let { errorMessage ->
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            // Login button
            Button(
                onClick = {
//                    // Check if email or password is empty
//                    if (emailState.value.isEmpty() || passwordState.value.isEmpty()) {
//                        emailErrorState.value = if (emailState.value.isEmpty()) "Email cannot be empty" else null
//                        passwordErrorState.value = if (passwordState.value.isEmpty()) "Password cannot be empty" else null
//                    } else {
//                        emailErrorState.value = null
//                        passwordErrorState.value = null
//                        loginViewModel.login(emailState.value, passwordState.value)
//                        navigateToHome()
//                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .padding(vertical = 16.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text(text = "SIGNUP")
            }

            Button(
                onClick = {
                    navigateToLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .padding(vertical = 16.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text(text = "LOGIN")
            }
        }
    }
}
