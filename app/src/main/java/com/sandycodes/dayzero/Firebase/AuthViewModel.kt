package com.sandycodes.dayzero.Firebase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authState = MutableLiveData<AuthState>(AuthState.Idle)
    val authState: LiveData<AuthState> = _authState

    fun login(email: String, password: String) {
        repository.login(email, password) {
            _authState.postValue(it)
        }
    }

    fun signup(email: String, password: String) {
        repository.signup(email, password) {
            _authState.postValue(it)
        }
    }

    fun checkLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }

    fun signInWithGoogle(context: Context) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val result = FirebaseGoogleAuthManager.signIn(context)
            _authState.value = result
        }
    }

}