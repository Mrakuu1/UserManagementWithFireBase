package com.usermgmt.admin.data.repository

import com.google.firebase.auth.FirebaseAuthException
import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.data.firebase.FirebaseAuthService
import com.usermgmt.admin.data.firebase.FirebaseDatabaseService
import com.usermgmt.admin.domain.model.User
import com.usermgmt.admin.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val firebaseDatabaseService: FirebaseDatabaseService
) : AuthRepository {

    override suspend fun registerAdmin(
        user: User,
        password: String
    ): AppResult<User> {

        return try {
            val uid = firebaseAuthService.createAdmin(user.email, password)

            val finalUser = user.copy(id = uid)

            firebaseDatabaseService.saveAdmin(uid,finalUser)

            AppResult.Success(finalUser)

        } catch (e:Exception){
            AppResult.Error(e.message ?: "Unknown error")
        }

    }

    override suspend fun loginAdmin(
        email: String,
        password: String
    ): AppResult<String> {
        return try {
            val result = firebaseAuthService.loginAdmin(email, password)
            AppResult.Success(result)
        } catch (e: FirebaseAuthException) {
            AppResult.Error(e.message ?: "Unknown error")
        }
    }

}