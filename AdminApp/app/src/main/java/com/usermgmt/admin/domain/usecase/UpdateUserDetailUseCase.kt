package com.usermgmt.admin.domain.usecase

import com.usermgmt.admin.domain.model.User
import com.usermgmt.admin.domain.repository.HomeRepository

class UpdateUserDetailUseCase(
    private val homeRepository: HomeRepository
)  {

    suspend fun invoke(user : User) = homeRepository.updateUser(user)
}