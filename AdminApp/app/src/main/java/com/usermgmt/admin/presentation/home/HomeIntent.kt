package com.usermgmt.admin.presentation.home


sealed interface HomeIntent {

    data class OnUserClick(val userId : String) : HomeIntent

}

