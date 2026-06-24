package com.usermgmt.admin.di


import com.usermgmt.admin.domain.repository.AuthRepository
import com.usermgmt.admin.domain.repository.HomeRepository
import com.usermgmt.admin.domain.usecase.FetchUserListUseCase
import com.usermgmt.admin.domain.usecase.AdminLoginUseCase
import com.usermgmt.admin.domain.usecase.DeleteUserUseCase
import com.usermgmt.admin.domain.usecase.RegisterUserUseCase
import com.usermgmt.admin.domain.usecase.UpdateUserDetailUseCase
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
    ) = RegisterUserUseCase(repository)

    @Provides
    fun provideLoginUseCase(
        repository: AuthRepository
    ) = AdminLoginUseCase(repository)

    @Provides
    fun provideFetchUserListUseCase(
        repository: HomeRepository
    ) = FetchUserListUseCase(repository)

    @Provides
    fun provideDeleteUserUseCase(
        repository: HomeRepository
    ) = DeleteUserUseCase(repository)

    @Provides
    fun provideUpdateUserDetailUseCase(
        repository: HomeRepository
    ) = UpdateUserDetailUseCase(repository)

}