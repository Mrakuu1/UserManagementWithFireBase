package com.usermgmt.admin.presentation.components


sealed class SnackbarMessage{

    data class Success(val message:String):SnackbarMessage()

    data class Error(val message:String):SnackbarMessage()

}