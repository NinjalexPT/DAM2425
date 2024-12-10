package com.example.newsapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(title: String, navController: NavController, isBaseScreen: Boolean) {
    TopAppBar(
        title = { Text(title)},
        navigationIcon = {
            if (!isBaseScreen) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        }


    )
}


@Preview(showBackground = true)
@Composable
fun MuTopAppBarPreview() {
    MyTopAppBar(
        "Newsapp",
        navController = TODO(),
        isBaseScreen = TODO()
    )
}