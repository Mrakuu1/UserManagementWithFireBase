package com.usermgmt.admin.presentation.auth.login


sealed interface LoginEffect {

    data object Success : LoginEffect
    data class ShowSnackbar(val message:String):LoginEffect
}

