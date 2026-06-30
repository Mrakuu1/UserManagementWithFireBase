package com.usermgmt.user.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.usermgmt.user.presentation.auth.login.LoginScreen
import com.usermgmt.user.presentation.auth.registration.RegisterScreen
import com.usermgmt.user.presentation.language.LanguageScreen

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
            route = Screens.Language.route
        ) {
            LanguageScreen(navController = navController)
        }
    }

}