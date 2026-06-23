package com.usermgmt.admin.presentation.home

sealed interface HomeEffect {

    data class Success(val userId : String) : HomeEffect
    data class ShowSnackbar(val message : String):HomeEffect
}

