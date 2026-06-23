package com.usermgmt.user.presentation.components


sealed class SnackbarMessage{

    data class Success(val message:String):SnackbarMessage()

    data class Error(val message:String):SnackbarMessage()

}