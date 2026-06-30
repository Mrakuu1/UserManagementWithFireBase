package com.usermgmt.user.presentation.auth.login


sealed interface LoginIntent {

    data object LoginUser : LoginIntent

    data object RegisterUser : LoginIntent

    data object LanguageSelection : LoginIntent

    data class UpdateUserName(val name:String) : LoginIntent

    data class UpdatePassword(val password:String) : LoginIntent
}

