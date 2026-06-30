package com.usermgmt.user.presentation.language

import com.usermgmt.user.domain.model.Language


data class LanguageState(

    val selectedLanguage : String = "en",

    val languageList : List<Language> = emptyList()

)

