package com.usermgmt.admin.data.firebase

import com.google.firebase.database.FirebaseDatabase
import com.usermgmt.admin.core.common.AppResult
import com.usermgmt.admin.core.constants.FirebaseConstants
import com.usermgmt.admin.domain.model.User
import kotlinx.coroutines.tasks.await

class FirebaseDatabaseService(
    private  val firebaseDatabase: FirebaseDatabase
) {

    suspend fun saveAdmin(
        userId : String,
        user : Any
    ) {
        firebaseDatabase
            .getReference(FirebaseConstants.ADMIN_TABLE)
            .child(userId)
            .setValue(user)
            .await()
    }

    suspend fun fetchUsersList(): List<User> {

        val reference = firebaseDatabase
            .getReference(FirebaseConstants.USER_TABLE)

        println("Firebase Path: ${reference}")

        val snapshot = reference
            .get()
            .await()

        return snapshot.children.mapNotNull {
            it.getValue(User::class.java)
        }
    }

    suspend fun updateUser(user : User) {
        firebaseDatabase
            .getReference(FirebaseConstants.USER_TABLE)
            .child(user.id)
            .setValue(user)
            .await()
    }

    suspend fun deleteUser(userId: String) {
        firebaseDatabase
            .getReference(FirebaseConstants.USER_TABLE)
            .child(userId)
            .removeValue()
            .await()
    }
}