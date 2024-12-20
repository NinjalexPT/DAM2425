package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.theme.NewsappTheme
import androidx.navigation.compose.composable
import com.example.newsapp.ui.Favorites.FavoritesView
import com.example.newsapp.ui.components.MyBottomBar
import com.example.newsapp.ui.components.MyTopAppBar
import com.example.newsapp.module.Article
import com.example.newsapp.ui.ArticleDetail
import com.example.newsapp.ui.Home.HomeView
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isBaseScreen by remember { mutableStateOf(true) }
            var article by remember { mutableStateOf<Article?>(null) }
            var title by remember { mutableStateOf("Daily News") }
            NewsappTheme {
                var navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MyTopAppBar(
                            navController = navController,
                            title,
                            isBaseScreen,
                            article)
                    },
                    bottomBar = {
                        MyBottomBar(
                            navController = navController
                        )
                    }
                ) { innerPadding ->
                    NavHost(navController = navController,
                        startDestination = Screen.Home.route ) {
                        composable(route = Screen.Home.route) {
                            isBaseScreen = true
                            article = null
                            title = "Home"
                            HomeView(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
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
                        composable(route = Screen.ArticleDetail.route) {
                            isBaseScreen = false
                            val articleJsonString = it.arguments?.getString("article")
                            article = Article.fromjson(JSONObject(articleJsonString!!))
                            ArticleDetail(
                                modifier = Modifier.padding(innerPadding),
                                article = article!!
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
    object Favorites : Screen("favorites")
    object ArticleDetail : Screen("article_detail/{article}")

}