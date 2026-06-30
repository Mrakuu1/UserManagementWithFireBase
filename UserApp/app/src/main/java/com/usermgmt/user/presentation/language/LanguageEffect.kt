package com.usermgmt.user.presentation.language


sealed interface LanguageEffect {

    data class ShowMessage(val message : String) : LanguageEffect

    data object NavigateBack : LanguageEffect

}

