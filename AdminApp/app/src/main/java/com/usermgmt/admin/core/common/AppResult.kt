package com.usermgmt.admin.core.common

sealed class AppResult<out T>{


    data class Success<T>(

        val data:T

    ):AppResult<T>()



    data class Error(

        val message:String

    ):AppResult<Nothing>()


}