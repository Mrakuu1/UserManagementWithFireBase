package com.usermgmt.user.domain.repository

import com.usermgmt.user.core.common.AppResult
import com.usermgmt.user.domain.model.User

interface AuthRepository {

    suspend fun registerUser(
        user: User,
        password:String
    ): AppResult<User>

    suspend fun loginUser(
        email:String,
        password:String
    ): AppResult<String>

}