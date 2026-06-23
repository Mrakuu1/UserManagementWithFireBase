package com.usermgmt.admin.data.repository

import android.util.Log
import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.data.firebase.FirebaseDatabaseService
import com.usermgmt.admin.domain.model.User
import com.usermgmt.admin.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val firebaseDatabaseService: FirebaseDatabaseService
) : HomeRepository {
    override suspend fun fetchUserList(): AppResult<List<User>> {

        return try {

            val users = firebaseDatabaseService.fetchUsersList()

            Log.d("Repository", "Users count: ${users.size}")

            AppResult.Success(users)

        } catch (e: Exception) {

            Log.e("Repository", e.toString())

            AppResult.Error(
                e.message ?: "Unknown error"
            )
        }
    }

    override suspend fun deleteUser(userId: String): AppResult<Unit> {
        return try {
            firebaseDatabaseService.deleteUser(userId)
            AppResult.Success(Unit)
        } catch (e : Exception) {
            AppResult.Error(e.message ?: "Unknown error")
        }
    }
}