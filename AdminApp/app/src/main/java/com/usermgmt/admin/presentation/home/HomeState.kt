package com.usermgmt.admin.presentation.home

import com.usermgmt.admin.domain.model.User


data class HomeState(

    val isLoading:Boolean = false,
    val users : List<User> = emptyList()
)

