package com.usermgmt.admin.domain.repository

import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.domain.model.User

interface HomeRepository {

    suspend fun fetchUserList(): AppResult<List<User>>

    suspend fun deleteUser(userId: String) : AppResult<Unit>

    suspend fun updateUser(userId: User) : AppResult<User>
}