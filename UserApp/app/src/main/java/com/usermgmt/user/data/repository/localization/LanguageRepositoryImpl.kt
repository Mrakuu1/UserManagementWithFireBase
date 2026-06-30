package com.usermgmt.user.data.repository.localization

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.usermgmt.user.domain.repository.LanguageRepository
import com.usermgmt.user.domain.repository.UserPreference
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(
    private val userPreference: UserPreference
) : LanguageRepository {

    override fun getLanguage() = userPreference.getLanguage()

    override suspend fun changeLanguage(
        language:String
    ){
        userPreference.saveLanguage(language)
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(language)
        )
    }

}