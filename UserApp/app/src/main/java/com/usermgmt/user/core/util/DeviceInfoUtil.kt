package com.usermgmt.user.core.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import com.usermgmt.user.domain.model.DeviceInfo

object DeviceInfoUtil {

    @SuppressLint("HardwareIds")
    fun getDeviceInfo(
        context: Context
    ): DeviceInfo {
        return DeviceInfo(
            deviceId = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            ),
            manufacturer = Build.MANUFACTURER,
            model = Build.MODEL,
            brand = Build.BRAND,
            androidVersion = Build.VERSION.RELEASE,
            sdkVersion = Build.VERSION.SDK_INT,
            appVersion = context.packageManager
                .getPackageInfo(
                    context.packageName,
                    0
                )
                .versionName ?: ""

        )

    }
}