package com.example.myshoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myshoppinglist.ui.MyTopBar
import com.example.myshoppinglist.ui.getScreenTitle
import com.example.myshoppinglist.ui.lists.AddListView
import com.example.myshoppinglist.ui.lists.ListListview
import com.example.myshoppinglist.ui.lists.items.AddItemView
import com.example.myshoppinglist.ui.lists.items.ListItemsView
import com.example.myshoppinglist.ui.login.LoginView
import com.example.myshoppinglist.ui.login.RegisterView
import com.example.myshoppinglist.ui.theme.MyShoppingListTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyShoppingListTheme {
                var navController = rememberNavController()

                // Observe the current route
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                // Determine if back navigation is possible
                val canNavigateBack = navController.previousBackStackEntry != null && (currentRoute != Screen.Home.route && currentRoute != Screen.Login.route)

                Scaffold(topBar = {
                    MyTopBar(
                        title = getScreenTitle(currentRoute),
                        canNavigateBack = canNavigateBack,
                        onBackClick = { navController.popBackStack() }
                    )
                },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Screen.Login.route
                    ){
                        composable(Screen.Login.route) {
                            LoginView(
                                modifier = Modifier.padding(innerPadding),
                                onLoginSuccess = {
                                    navController.navigate(Screen.Home.route)
                                },navController = navController
                            )
                        }
                        composable(Screen.Home.route) {
                            ListListview(
                                navController = navController
                            )
                        }
                        composable (Screen.AddList.route){
                            AddListView(navController = navController)
                        }
                        composable (Screen.Register.route){
                            RegisterView(navController = navController)
                        }
                        composable(Screen.ListItems.route) {
                            val listId = it.arguments?.getString("listId")
                            ListItemsView(
                                modifier = Modifier.padding(innerPadding),
                                listId = listId ?: "",
                                navController = navController
                            )
                        }
                        composable(Screen.AddItem.route){
                            val listId = it.arguments?.getString("listId")
                            AddItemView(modifier = Modifier.padding(innerPadding),
                                listId = listId ?: "",
                                navController = navController)
                        }
                    }
                }
                LaunchedEffect(Unit) {val auth = Firebase.auth
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        navController.navigate(Screen.Home.route)
                    } else
                        navController.navigate(Screen.Login.route)
                }
            }
        }
    }
}


sealed class Screen(val route: String){
    object Login : Screen("login")
    object Home : Screen("home")
    object AddList : Screen("add_list")
    object ListItems : Screen("list_items/{listId}")
    object Register : Screen("register")
    object AddItem : Screen("add_item/{listId}")

}

