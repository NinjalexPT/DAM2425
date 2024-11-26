package com.example.myshoppinglist.ui.lists

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myshoppinglist.models.ListItems
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

data class AddListState(
    val name : String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddListViewModel : ViewModel(){

    var state = mutableStateOf(AddListState())
        private set

    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun addList(){

        val db = Firebase.firestore
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        // Add a new document with a generated id.

        val listItems = ListItems(
            "",
            state.value.name,
            listOf(userId?:"")
        )

        db.collection("lists")
            .add(listItems)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

}