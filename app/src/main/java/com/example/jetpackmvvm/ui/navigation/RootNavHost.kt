package com.example.jetpackmvvm.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackmvvm.data.repository.auth.AuthRepository
import com.example.jetpackmvvm.ui.navigation.bottomappbar.BottomBar
import com.example.jetpackmvvm.ui.navigation.topappbar.AppTopBar
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavHost(authRepository: AuthRepository) {
    val isAuthenticated = remember {
        authRepository.isAuthenticated()
    }
    val topAppbarTitle = remember { mutableStateOf("") }
    val topAppBarState = rememberTopAppBarState()
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)
    val snackbarHostState = remember { SnackbarHostState() }

    val showTopBarState = rememberSaveable { (mutableStateOf(true)) }

    val showBottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val rootNavHostController = rememberNavController()
    val rootNavBackStackEntry by rootNavHostController.currentBackStackEntryAsState()

    when (rootNavBackStackEntry?.destination?.route) {
        AppScreen.Main.Home.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = stringResource(AppScreen.Main.Home.title!!)

        }

        AppScreen.Main.Profile.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = stringResource(AppScreen.Main.Profile.title!!)

        }

        AppScreen.Main.Notification.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = stringResource(AppScreen.Main.Notification.title!!)
        }


        else -> {
            showBottomBarState.value = false
            showTopBarState.value = false
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(barScrollBehavior.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            if(showTopBarState.value) {
                AppTopBar(
                    toolbarTitle = topAppbarTitle.value,
                    barScrollBehavior = barScrollBehavior
                )
            }else{
                Box {}
            }
        },

        bottomBar = {
            if(showBottomBarState.value) {
                BottomBar(navController = rootNavHostController)
            }
        }
    ) {paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavHost(
                navController = rootNavHostController,
                startDestination = if(isAuthenticated) AppScreen.Main.route else AppScreen.Auth.route,
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                }
            ){
                authNavGraph(rootNavHostController)
                mainNavGraph(rootNavHostController, rootNavBackStackEntry)
            }
        }

    }
}