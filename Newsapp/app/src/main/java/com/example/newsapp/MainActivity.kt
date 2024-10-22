package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.theme.NewsappTheme
import androidx.navigation.compose.composable
import com.example.newsapp.ArticleDetail
import com.example.newsapp.NewsApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsappTheme {
                var navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            NewsApp(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable(route = Screen.ArticleDetail.route) {
                            val url = it.arguments?.getString("articleUrl")
                            ArticleDetail(
                                modifier = Modifier.padding(innerPadding),
                                url = url ?: ""

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

}