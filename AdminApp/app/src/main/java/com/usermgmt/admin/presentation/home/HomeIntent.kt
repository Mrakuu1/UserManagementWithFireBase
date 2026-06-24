package com.usermgmt.admin.presentation.home

import com.usermgmt.admin.domain.model.User


sealed interface HomeIntent {

    data class OnUserClick(val userId : String) : HomeIntent

    data object OnEmailClick : HomeIntent

    data class EditUser(val user: User):HomeIntent

    data class UpdateUser(val user: User):HomeIntent

    data object SaveUpdate:HomeIntent

    data object CancelEdit:HomeIntent

    data class DeleteUser(val userId:String):HomeIntent

}

