package com.usermgmt.admin.di

import com.usermgmt.admin.data.repository.AuthRepositoryImpl
import com.usermgmt.admin.data.repository.HomeRepositoryImpl
import com.usermgmt.admin.domain.repository.AuthRepository
import com.usermgmt.admin.domain.repository.HomeRepository
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
    abstract fun bindHomeRepository(
        impl : HomeRepositoryImpl
    ) : HomeRepository

}