package com.usermgmt.admin.presentation.auth.registration

sealed interface RegistrationEffect {
    data object Success : RegistrationEffect
    data class ShowSnackbar(val message:String):RegistrationEffect

}
