package com.usermgmt.user.domain.model

data class User(

    val id:String = "",

    val name:String = "",

    val email:String = "",

    val phone:String = "",

    val password:String = "",

    val role:String = "USER",

    val createdAt : Long = System.currentTimeMillis()

)
