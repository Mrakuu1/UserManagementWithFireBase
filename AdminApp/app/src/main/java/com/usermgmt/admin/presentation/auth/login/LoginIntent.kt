package com.usermgmt.admin.presentation.auth.login


sealed interface LoginIntent {

    data object LoginUser : LoginIntent

    data class UpdateUserName(val name:String) : LoginIntent

    data class UpdatePassword(val password:String) : LoginIntent
}

