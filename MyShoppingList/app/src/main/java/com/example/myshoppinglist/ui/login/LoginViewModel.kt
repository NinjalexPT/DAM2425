package com.example.myshoppinglist.ui.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class LoginViewModel : ViewModel() {

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
            return false
        }else return true
    }

    fun onLoginClick( onLoginSuccess: ()->Unit) {
        state.value = state.value.copy(isLoading = true)

        val auth: FirebaseAuth = Firebase.auth
        if (userinserted()) {
            auth.signInWithEmailAndPassword(state.value.email, state.value.password)
                .addOnCompleteListener { task ->
                    state.value = state.value.copy(isLoading = false)
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        onLoginSuccess()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        state.value =
                            state.value.copy(error = task.exception?.message ?: "Unknown error")
                    }
                }

        }
        else{state.value = state.value.copy(isLoading = false)
            state.value = state.value.copy(error = "Email and password cannot be empty")}

    }
}