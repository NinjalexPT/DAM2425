package com.example.myshoppinglist.ui.lists.items

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myshoppinglist.models.Item
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

data class ListItemsState(
    val items : List<Item> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ListItemsViewModel : ViewModel(){

    var state = mutableStateOf(ListItemsState())
        private set


    fun getItems(listId : String){

        val db = Firebase.firestore

        db.collection("lists")
            .document(listId)
            .collection("items")
            .addSnapshotListener{ value, error->
                if (error!=null){
                    state.value = state.value.copy(
                        error = error.message
                    )
                    return@addSnapshotListener
                }

                val items = arrayListOf<Item>()
                for (document in value?.documents!!) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val item = document.toObject(Item::class.java)
                    item?.DocId = document.id
                    items.add(item!!)
                }
                state.value = state.value.copy(
                    items = items
                )
            }

    }

    fun toggleItemChecked(listId: String, item: Item){
        val db = Firebase.firestore

        item.checked = !item.checked

        db.collection("lists")
            .document(listId)
            .collection("items")
            .document(item.DocId!!)
            .set(item)

    }


}