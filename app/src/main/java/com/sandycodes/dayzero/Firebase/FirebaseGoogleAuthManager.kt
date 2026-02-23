package com.sandycodes.dayzero.Firebase

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.sandycodes.dayzero.R
import kotlinx.coroutines.tasks.await

object FirebaseGoogleAuthManager {

    suspend fun signIn(context: Context): AuthState {

        return try {

            val credentialManager = CredentialManager.create(context)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(
                    context.getString(R.string.default_web_client_id)
                )
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(
                request = request,
                context = context
            )

            val credential = result.credential

            if (credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {

                val googleIdTokenCredential =
                    GoogleIdTokenCredential.createFrom(credential.data)

                firebaseSignIn(googleIdTokenCredential.idToken)

            } else {
                AuthState.Error("Invalid credential type")
            }

        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Google sign in failed")
        }
    }

    private suspend fun firebaseSignIn(idToken: String): AuthState {

        return try {

            val credential = GoogleAuthProvider.getCredential(idToken, null)

            val authResult = FirebaseAuth.getInstance()
                .signInWithCredential(credential)
                .await()   // requires kotlinx-coroutines-play-services

            AuthState.Success(authResult.user?.uid ?: "")

        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Firebase auth failed")
        }
    }
}