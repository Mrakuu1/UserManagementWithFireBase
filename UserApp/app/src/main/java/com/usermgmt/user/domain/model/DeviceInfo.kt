package com.usermgmt.user.domain.model

data class DeviceInfo(
    val deviceId: String,
    val manufacturer: String,
    val model: String,
    val brand: String,
    val androidVersion: String,
    val sdkVersion: Int,
    val appVersion: String
)