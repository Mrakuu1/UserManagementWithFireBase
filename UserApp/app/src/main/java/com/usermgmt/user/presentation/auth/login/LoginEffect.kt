package com.usermgmt.user.presentation.auth.login


sealed interface LoginEffect {

    data object Success : LoginEffect
    data class ShowSnackbar(val message:String):LoginEffect
}

