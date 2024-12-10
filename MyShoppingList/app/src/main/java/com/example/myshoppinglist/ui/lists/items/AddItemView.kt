package com.example.myshoppinglist.ui.lists.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddItemView(
    modifier: Modifier = Modifier,
    listId : String,
    navController: NavController = rememberNavController()
    )
        {
        val viewModel : AddItemViewModel = viewModel()
            val state = viewModel.state.value


        Column(modifier = modifier.fillMaxSize()) {
            TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("enter item name")
            },
            value = state.name,
            onValueChange = {viewModel.onNameChange(it)}
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text("Enter quantity")
                },
                value = if(state.qtd == 0.0)"" else state.qtd.toString(),
                onValueChange = { newQtd ->
                    val qtd = newQtd.toDoubleOrNull() ?: 0.0 // Convert string to double, default to 0.0
                    viewModel.onQtdChange(qtd)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                viewModel.addItem(listId)
                navController.popBackStack()
            }) {
            Text("add")
        }
    }
}