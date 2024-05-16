package com.example.jetpackmvvm.ui.navigation.bottomappbar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackmvvm.ui.navigation.AppScreen

@Composable
fun BottomBar(
    navController: NavHostController
){
    val navigationScreen = listOf(
        AppScreen.Main.Home,
        AppScreen.Main.Notification,
        AppScreen.Main.Profile,
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationScreen.forEach { item->

            NavigationBarItem(
                selected = currentRoute == item.route ,
                label = {
                    Text(
                        text = stringResource(id = item.title!!),
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(AppScreen.Main.Home.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
//                          navController.navigate(item.route) {
//                              popUpTo(navController.graph.findStartDestination().id) {
//                                  saveState = true
//                              }
//                              launchSingleTop = true
//                              restoreState = true
//                          }
                },
                icon = {
                    Icon(
                        imageVector = (if (item.route == currentRoute) item.selectedIcon else item.unselectedIcon)!!,
                        contentDescription = stringResource(id = item.title!!)
                    )
                }
            )
        }
    }
}