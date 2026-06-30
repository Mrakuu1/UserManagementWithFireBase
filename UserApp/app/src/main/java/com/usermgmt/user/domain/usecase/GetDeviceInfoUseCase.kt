package com.usermgmt.user.domain.usecase

import com.usermgmt.user.domain.model.DeviceInfo
import com.usermgmt.user.domain.repository.device.DeviceInfoProvider
import javax.inject.Inject

class GetDeviceInfoUseCase @Inject constructor(
    private val deviceIdProvider: DeviceInfoProvider
){
    operator fun invoke(): DeviceInfo {
        return deviceIdProvider.getDeviceInfo()
    }

}