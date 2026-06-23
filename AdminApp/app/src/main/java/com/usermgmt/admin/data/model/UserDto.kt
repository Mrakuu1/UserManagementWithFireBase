package com.usermgmt.admin.data.model

import com.usermgmt.admin.domain.model.User

data class UserDto(
    val id:String="",
    val name:String="",
    val email:String="",
    val phone:String="",
    val password:String="",
    val role:String="ADMIN",
    val createdAt:Long=0
)

fun UserDto.toDomain()=

    User(
        id,
        name,
        email,
        phone,
        password,
        role,
        createdAt
    )