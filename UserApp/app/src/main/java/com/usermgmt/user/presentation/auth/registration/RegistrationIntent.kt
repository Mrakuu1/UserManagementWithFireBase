package com.usermgmt.user.presentation.auth.registration

sealed interface RegistrationIntent {

    object RegisterUser : RegistrationIntent

    data class UpdateUserName(val name: String) : RegistrationIntent

    data class UpdateEMail(val email: String) : RegistrationIntent

    data class UpdatePhoneNumber(val phone: String) : RegistrationIntent

    data class UpdatePassword(val password: String) : RegistrationIntent
}
