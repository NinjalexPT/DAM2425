package com.example.myshoppinglist.ui.lists.items

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myshoppinglist.models.Item
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

data class AddItemState (
    val name : String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val qtd : Double? = 0.0
)

class AddItemViewModel : ViewModel(){

    var state = mutableStateOf(AddItemState())
    private set

    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun onQtdChange(qtd: Double) {

        state.value = state.value.copy(qtd = qtd)
    }

    fun addItem(listid : String){

        val db = Firebase.firestore
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        // Add a new document with a generated id.

        val Item = Item(
            DocId = "",
            name = state.value.name,
            qtd = state.value.qtd,
            checked = false
        )

        db.collection("lists/$listid/items")
            .add(Item)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

}