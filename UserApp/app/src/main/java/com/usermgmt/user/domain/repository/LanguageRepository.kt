package com.usermgmt.user.domain.repository

import kotlinx.coroutines.flow.Flow

interface LanguageRepository {

    fun getLanguage(): Flow<String>

    suspend fun changeLanguage(
        language:String
    )
}