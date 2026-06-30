package com.usermgmt.user.presentation.auth.login


sealed interface LoginEffect {

    data object Success : LoginEffect
    data object RegisterUser : LoginEffect
    data object LanguageSelection : LoginEffect
    data class ShowSnackbar(val message:String):LoginEffect
}

