package com.usermgmt.admin.presentation.home

import com.usermgmt.admin.domain.model.User


data class HomeState(

    val isLoading:Boolean = false,
    val users : List<User> = emptyList(),
    val expandedUserId:String? = null,
    val editingUserId:String? = null,
    val editingUser:User? = null
)

