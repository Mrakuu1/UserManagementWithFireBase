package com.usermgmt.user.data.repository.device

import android.annotation.SuppressLint
import android.content.Context
import com.usermgmt.user.core.util.DeviceInfoUtil
import com.usermgmt.user.domain.model.DeviceInfo
import com.usermgmt.user.domain.repository.device.DeviceInfoProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DeviceIdProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DeviceInfoProvider {

    @SuppressLint("HardwareIds")
    override fun getDeviceInfo(): DeviceInfo {
        return DeviceInfoUtil.getDeviceInfo(context)
    }

}