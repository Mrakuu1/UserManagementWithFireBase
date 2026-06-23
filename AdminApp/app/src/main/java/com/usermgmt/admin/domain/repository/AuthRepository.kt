package com.usermgmt.admin.domain.repository

import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.domain.model.User

interface AuthRepository {

    suspend fun registerAdmin(
        user: User,
        password:String
    ): AppResult<User>

    suspend fun loginAdmin(
        email:String,
        password:String
    ): AppResult<String>

}