package com.example.myshoppinglist.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myshoppinglist.Screen
import com.example.myshoppinglist.ui.theme.MyShoppingListTheme

@Composable
fun RegisterView(modifier: Modifier = Modifier, navController: NavController = rememberNavController()) {

    val viewModel: RegisterViewModel = viewModel()
    val state = viewModel.state.value

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = state.email,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
                placeholder = {
                    Text("email")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = state.password,
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
                placeholder = {
                    Text("password")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = state.passwordConfirmation,
                onValueChange = {
                    viewModel.onPasswordConfirmationChange(it)
                },
                placeholder = {Text("password confirmation")})
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (viewModel.CheckPasswords())
                    viewModel.onRegisterClick()


                    if (state.error == "") {
                        navController.navigate(Screen.Home.route)
                    }
                },
                content = {
                    Text("Register")
                })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(Screen.Login.route) },
                content = {
                    Text("Back")
                })

            Spacer(modifier = Modifier.height(16.dp))
            if (state.error != null)
                Text(state.error ?: "")
            if (state.isLoading)
                CircularProgressIndicator()


        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
    MyShoppingListTheme() {
        RegisterView()
    }
}