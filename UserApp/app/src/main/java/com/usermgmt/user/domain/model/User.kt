package com.usermgmt.user.domain.model

import com.usermgmt.user.core.util.DeviceInfo

data class User(

    val id:String = "",

    val name:String = "",

    val email:String = "",

    val phone:String = "",

    val password:String = "",

    val role:String = "USER",

    val createdAt : Long = 0L,

    val deviceData: DeviceInfo? = null
)
