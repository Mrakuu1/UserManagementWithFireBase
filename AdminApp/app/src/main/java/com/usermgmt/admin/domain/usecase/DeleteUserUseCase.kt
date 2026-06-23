package com.usermgmt.admin.domain.usecase

import com.usermgmt.admin.domain.repository.HomeRepository

class DeleteUserUseCase(
   private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(userId: String) = homeRepository.deleteUser(userId)

}