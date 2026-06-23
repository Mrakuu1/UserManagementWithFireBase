package com.usermgmt.user.data.firebase

import com.google.firebase.database.FirebaseDatabase
import com.usermgmt.user.core.constants.FirebaseConstants
import kotlinx.coroutines.tasks.await

class FirebaseDatabaseService(
    private  val firebaseDatabase: FirebaseDatabase
) {

    suspend fun saveUser(
        userId : String,
        user : Any
    ) {
        firebaseDatabase
            .getReference(FirebaseConstants.USER_TABLE)
            .child(userId)
            .setValue(user)
            .await()
    }

}