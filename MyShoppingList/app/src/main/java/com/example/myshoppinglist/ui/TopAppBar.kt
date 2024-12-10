package com.example.myshoppinglist.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.myshoppinglist.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title: String,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = if (canNavigateBack) {
            {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {{}},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}


fun getScreenTitle(route: String?): String {
    return when (route) {
        Screen.Login.route -> "Login"
        Screen.Home.route -> "Home"
        Screen.AddList.route -> "Add List"
        Screen.Register.route -> "Register"
        Screen.ListItems.route -> "List Items"
        Screen.AddItem.route -> "Add Item"
        else -> "My App"
    }
}
