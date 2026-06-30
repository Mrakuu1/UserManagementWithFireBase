package com.usermgmt.user.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.usermgmt.user.domain.repository.UserPreference
import com.usermgmt.user.core.preferences.userDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferenceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserPreference {

    object Constants {

        val LANGUAGE = stringPreferencesKey("language")

        val USER_ID = stringPreferencesKey("user_id")
    }

    override suspend fun saveLanguage(language: String) {
        context.userDataStore.edit { it[Constants.LANGUAGE] = language  }
    }

    override fun getLanguage(): Flow<String> {
        return context.userDataStore.data.map { it[Constants.LANGUAGE] ?: "en" }
    }

    override suspend fun saveUserId(userId: String) {
        context.userDataStore.edit { it[Constants.USER_ID] = userId  }
    }

    override fun getUserId(): Flow<String> {
        return context.userDataStore.data.map { it[Constants.USER_ID] ?: "" }
    }

    override suspend fun clear() {
        context.userDataStore.edit { it.clear() }
    }

}