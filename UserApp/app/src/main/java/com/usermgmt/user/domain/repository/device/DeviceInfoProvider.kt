package com.usermgmt.user.domain.repository.device

import com.usermgmt.user.domain.model.DeviceInfo

interface DeviceInfoProvider {
    fun getDeviceInfo(): DeviceInfo
}