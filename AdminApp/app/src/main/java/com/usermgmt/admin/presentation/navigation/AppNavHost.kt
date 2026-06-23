package com.usermgmt.admin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.usermgmt.admin.presentation.auth.login.LoginScreen
import com.usermgmt.admin.presentation.auth.registration.RegisterScreen
import com.usermgmt.admin.presentation.home.HomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {

        composable(
            route = Screens.Login.route
        ) {
            LoginScreen(navController = navController)
        }

        composable(
            route = Screens.Register.route
        ) {
            RegisterScreen(navController = navController)
        }

        composable(
            route = Screens.Home.route
        ) {
            HomeScreen(onUserItemClick = {})
        }
    }

}