package com.usermgmt.user.data.repository

import com.google.firebase.auth.FirebaseAuthException
import com.usermgmt.user.core.common.AppResult
import com.usermgmt.user.data.firebase.FirebaseAuthService
import com.usermgmt.user.data.firebase.FirebaseDatabaseService
import com.usermgmt.user.domain.model.User
import com.usermgmt.user.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val firebaseDatabaseService: FirebaseDatabaseService
) : AuthRepository {

    override suspend fun registerUser(
        user: User,
        password: String
    ): AppResult<User> {

        return try {
            val uid = firebaseAuthService.createUser(user.email, password)

            val finalUser = user.copy(id = uid)

            firebaseDatabaseService.saveUser(uid,finalUser)

            AppResult.Success(finalUser)

        } catch (e:Exception){
            AppResult.Error(e.message ?: "Unknown error")
        }

    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): AppResult<String> {
        return try {
            val result = firebaseAuthService.loginUser(email, password)
            AppResult.Success(result)
        } catch (e: FirebaseAuthException) {
            AppResult.Error(e.message ?: "Unknown error")
        }
    }

}