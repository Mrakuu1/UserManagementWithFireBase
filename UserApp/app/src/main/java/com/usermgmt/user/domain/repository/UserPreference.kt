package com.usermgmt.user.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreference {

    suspend fun saveLanguage(language:String)

    fun getLanguage(): Flow<String>

    suspend fun saveUserId(userId:String)

    fun getUserId(): Flow<String>

    suspend fun clear()

}