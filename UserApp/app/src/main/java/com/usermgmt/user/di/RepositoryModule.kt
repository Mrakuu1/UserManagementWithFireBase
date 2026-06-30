package com.usermgmt.user.di

import com.usermgmt.user.data.repository.AuthRepositoryImpl
import com.usermgmt.user.data.repository.UserPreferenceImpl
import com.usermgmt.user.data.repository.device.DeviceIdProviderImpl
import com.usermgmt.user.data.repository.localization.LanguageRepositoryImpl
import com.usermgmt.user.domain.repository.AuthRepository
import com.usermgmt.user.domain.repository.LanguageRepository
import com.usermgmt.user.domain.repository.UserPreference
import com.usermgmt.user.domain.repository.device.DeviceInfoProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindDeviceProvider(
        impl: DeviceIdProviderImpl
    ): DeviceInfoProvider

    @Binds
    @Singleton
    abstract fun bindUserPreference(
        impl: UserPreferenceImpl
    ) : UserPreference

    @Binds
    @Singleton
    abstract fun bindLanguageRepository(
        impl: LanguageRepositoryImpl
    ): LanguageRepository
}