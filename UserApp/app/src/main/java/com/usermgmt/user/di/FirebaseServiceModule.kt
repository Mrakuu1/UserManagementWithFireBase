package com.usermgmt.user.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.usermgmt.user.data.firebase.FirebaseAuthService
import com.usermgmt.user.data.firebase.FirebaseDatabaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseServiceModule {

    @Provides
    @Singleton
    fun provideAuthService(
        auth:FirebaseAuth
    ): FirebaseAuthService {
        return FirebaseAuthService(auth)
    }

    @Provides
    @Singleton
    fun provideDatabaseService(
        database:FirebaseDatabase
    ): FirebaseDatabaseService {
        return FirebaseDatabaseService(database)
    }

}