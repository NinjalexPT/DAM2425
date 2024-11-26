package com.example.myshoppinglist.ui.lists.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddItemView(
    modifier: Modifier = Modifier,
    listId : String,
    navController: NavController = rememberNavController()
    )
        {
        Column(modifier = modifier.fillMaxSize()) {
            TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("enter item name")
            },
            value = "",
            onValueChange = {}
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {

                navController.popBackStack()
            }) {
            Text("add")
        }
    }
}