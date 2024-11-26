package com.example.myshoppinglist.ui.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class RegisterViewModel : ViewModel() {

    var state = mutableStateOf(LoginState())
        private set

    fun onEmailChange(email: String) {
        state.value = state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state.value = state.value.copy(password = password)
    }

    fun userinserted(): Boolean {
        if (state.value.email.isEmpty() || state.value.password.isEmpty()) {

            state.value = state.value.copy(error = "Email and password cannot be empty")
            return false
        }else return true
    }

    fun onRegisterClick() {
        state.value = state.value.copy(isLoading = true)

        val auth: FirebaseAuth = Firebase.auth

        if (userinserted()) {
            auth.createUserWithEmailAndPassword(state.value.email, state.value.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        state.value =
                            state.value.copy(error = task.exception?.message ?: "Unknown error")
                    }
                }

            }
            else{state.value = state.value.copy(isLoading = false)}
        }

    }