package com.example.jetpackmvvm.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetpackmvvm.ui.screens.auth.login.LoginScreen
import com.example.jetpackmvvm.ui.screens.auth.signup.SignupScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = AppScreen.Auth.Login.route,
        route = AppScreen.Auth.route
    ){

        composable(
            route = AppScreen.Auth.Login.route
        ){
            LoginScreen(

                navigateToHome = {
                    navController.navigate(AppScreen.Main.route) {
                        popUpTo(AppScreen.Auth.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignUp = {

                    navController.navigate(AppScreen.Auth.Register.route)
                }
            )
        }

        composable(
            route = AppScreen.Auth.Register.route
        ){
            SignupScreen(
                onNavigateBack =  {navController.navigateUp()}
            )
        }
    }
}