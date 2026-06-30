package com.usermgmt.user.core.preferences

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.userDataStore by preferencesDataStore(
    name = "user_preferences"
)