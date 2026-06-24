package com.usermgmt.user.di


import com.usermgmt.user.domain.repository.AuthRepository
import com.usermgmt.user.domain.repository.device.DeviceInfoProvider
import com.usermgmt.user.domain.usecase.GetDeviceInfoUseCase
import com.usermgmt.user.domain.usecase.LoginUseCase
import com.usermgmt.user.domain.usecase.RegisterUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideRegisterUseCase(
        repository: AuthRepository
    ): RegisterUserUseCase {
        return RegisterUserUseCase(repository)
    }

    @Provides
    fun provideLoginUseCase(
        repository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    fun provideGetDeviceInfoUseCase(
        repository : DeviceInfoProvider
    ) = GetDeviceInfoUseCase(repository)

}