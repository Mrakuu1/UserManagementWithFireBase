package com.usermgmt.admin.presentation.auth.login


data class LoginState(

    val isLoading:Boolean = false,

    val name:String = "",

    val password:String = "",
)

