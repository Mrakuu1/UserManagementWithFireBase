package com.usermgmt.user.presentation.navigation

sealed class Screens(
    val route:String
){
    data object Login :
        Screens("login")

    data object Register :
        Screens("register")


    data object Profile :
        Screens("profile")

    data object Language :
        Screens("language")
}