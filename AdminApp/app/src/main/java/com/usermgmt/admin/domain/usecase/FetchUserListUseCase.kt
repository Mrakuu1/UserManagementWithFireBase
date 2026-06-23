package com.usermgmt.admin.domain.usecase

import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.domain.model.User
import com.usermgmt.admin.domain.repository.HomeRepository

class FetchUserListUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): AppResult<List<User>> {
        return repository.fetchUserList()
    }
}