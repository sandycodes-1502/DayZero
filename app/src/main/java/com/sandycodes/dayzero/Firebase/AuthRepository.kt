package com.sandycodes.dayzero.Firebase

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun login(
        email: String,
        password: String,
        onResult: (AuthState) -> Unit
    ){
        onResult(AuthState.Loading)

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val uid = it.user?.uid ?: ""
                onResult(AuthState.Success(uid))
            }
            .addOnFailureListener {
                onResult(AuthState.Error(it.message ?: "Login Failed"))
            }
    }

    fun signup(
        email: String,
        password: String,
        onResult: (AuthState) -> Unit
    ) {
        onResult(AuthState.Loading)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val uid = it.user?.uid ?: ""
                onResult(AuthState.Success(uid))
            }
            .addOnFailureListener {
                onResult(AuthState.Error(it.message ?: "Signup failed"))
            }

    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

}