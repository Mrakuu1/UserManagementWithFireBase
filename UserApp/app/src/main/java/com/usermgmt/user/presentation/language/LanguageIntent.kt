package com.usermgmt.user.presentation.language


sealed interface LanguageIntent {

    data class ChangeLanguage(val code : String) : LanguageIntent

    data object NavigateBack : LanguageIntent

}

