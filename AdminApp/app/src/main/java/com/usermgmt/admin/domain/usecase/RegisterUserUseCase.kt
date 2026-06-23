package com.usermgmt.admin.domain.usecase

import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.domain.model.User
import com.usermgmt.admin.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        user: User,
        password: String
    ): AppResult<User> {
        return repository.registerAdmin(user, password)
    }

}