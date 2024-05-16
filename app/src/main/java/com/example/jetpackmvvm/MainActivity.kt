package com.example.jetpackmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.jetpackmvvm.data.repository.auth.AuthRepository
import com.example.jetpackmvvm.ui.navigation.RootNavHost
import com.example.jetpackmvvm.ui.theme.JetpackMvvmTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authRepository: AuthRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackMvvmTheme {
                RootNavHost(authRepository)
            }
        }
    }
}

