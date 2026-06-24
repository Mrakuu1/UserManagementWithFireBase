package com.usermgmt.user.presentation.auth.registration

data class RegistrationState(
    var isLoading : Boolean = false,
    var id: String = "",
    var name: String = "",
    var nameError: String = "",
    var email: String = "",
    var emailError: String = "",
    var phone: String = "",
    var phoneError: String = "",
    var password: String = "",
    var passwordError: String = "",
    var role: String = "USER",
    var createdAt: Long = System.currentTimeMillis(),
)