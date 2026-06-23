package com.usermgmt.user.presentation.auth.registration

sealed interface RegistrationEffect {
    data object Success : RegistrationEffect
    data class ShowSnackbar(val message:String):RegistrationEffect

}
