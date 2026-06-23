package com.usermgmt.user.domain.usecase

import com.usermgmt.user.core.common.AppResult
import com.usermgmt.user.domain.model.User
import com.usermgmt.user.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        user: User,
        password: String
    ): AppResult<User> {
        return repository.registerUser(user, password)
    }

}