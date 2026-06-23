package com.usermgmt.admin.domain.usecase

import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.domain.repository.AuthRepository

class AdminLoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): AppResult<String> {
        return repository.loginAdmin(email, password)
    }
}