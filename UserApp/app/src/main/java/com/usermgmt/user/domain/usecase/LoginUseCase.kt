package com.usermgmt.user.domain.usecase

import com.usermgmt.user.core.common.AppResult
import com.usermgmt.user.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): AppResult<String> {
        return repository.loginUser(email, password)
    }
}