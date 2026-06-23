package com.usermgmt.admin.data.firebase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthService(
    private val firebaseAuth:FirebaseAuth
) {
    suspend fun createAdmin(
        email: String,
        password: String
    ): String {
        return firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
            .user!!
            .uid
    }

    suspend fun loginAdmin(
        email: String,
        password: String
    ): String {
        return firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
            .user!!
            .uid
    }

}