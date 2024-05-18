package com.example.jetpackmvvm.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.example.jetpackmvvm.ui.screens.home.HomeScreen
import com.example.jetpackmvvm.ui.screens.home.productdetails.ProductDetailScreen
import com.example.jetpackmvvm.ui.screens.notification.NotificationScreen
import com.example.jetpackmvvm.ui.screens.profile.ProfileScreen

fun NavGraphBuilder.mainNavGraph(
    navHostController: NavHostController,
    roNavBackStackEntry: NavBackStackEntry?
){

    navigation(
        startDestination = AppScreen.Main.Home.route,
        route = AppScreen.Main.route
    ){

        composable(
            route = AppScreen.Main.Home.route,
            enterTransition = {
                return@composable fadeIn(tween(1000))
            },
            exitTransition = {
                return@composable fadeOut(tween(700))
            },
        ){
            HomeScreen(
                onProductClick = {
                    val route = AppScreen.Main.ProductDetails.createRoute(productId = it)
                    navHostController.navigate(route)
                }
            )
        }

        composable(
            route = AppScreen.Main.Notification.route,
            enterTransition = {
                return@composable fadeIn(tween(1000))
            },
            exitTransition = {
                return@composable fadeOut(tween(700))
            }
        ){
            NotificationScreen()
        }

        composable(
            route = AppScreen.Main.Profile.route,
            enterTransition = {
                return@composable fadeIn(tween(1000))
            },
            exitTransition = {
                return@composable fadeOut(tween(700))
            }
        ){
            ProfileScreen(
                onLogout = {
                    navHostController.navigate(AppScreen.Auth.route) {
                        popUpTo(AppScreen.Main.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        dialog(
            route = AppScreen.Main.ProductDetails.route,
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)

        ) {

            // value also can be  retrieve directly from responsible view-model
            val productId = roNavBackStackEntry?.arguments?.getString("productId")
            if (productId != null){
                ProductDetailScreen(productId = productId) {
                    navHostController.navigateUp()
                }
            }
        }

    }
}