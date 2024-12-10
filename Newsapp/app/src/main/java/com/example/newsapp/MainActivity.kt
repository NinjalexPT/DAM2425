package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.theme.NewsappTheme
import androidx.navigation.compose.composable
import com.example.newsapp.Favorites.FavoritesView
import com.example.newsapp.components.MyBottomBar
import com.example.newsapp.components.MyTopAppBar

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isBaseScreen : Boolean by remember { mutableStateOf(true) }
            var article by remember { mutableStateOf(null) }
            NewsappTheme {
                var navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = { MyTopAppBar("News", navController,isBaseScreen) },
                    bottomBar = {
                        MyBottomBar(
                            navController = navController
                        )}
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            NewsApp(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                            isBaseScreen = true
                        }
                        composable(route = Screen.ArticleDetail.route) {
                            val url = it.arguments?.getString("articleUrl")
                            ArticleDetail(
                                modifier = Modifier.padding(innerPadding),
                                url = url ?: ""

                            )
                            isBaseScreen = false
                        }
                        composable(route = Screen.Favorites.route) {
                            isBaseScreen = true
                            article = null
                            title = "Favorites"
                            FavoritesView(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ArticleDetail : Screen("article_detail/{articleUrl}")
    object Favorites : Screen("favorites")
}