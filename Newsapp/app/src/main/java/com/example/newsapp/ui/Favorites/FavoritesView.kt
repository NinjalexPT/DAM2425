package com.example.newsapp.ui.Favorites

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.Screen
import com.example.newsapp.Screen.*
import com.example.newsapp.ui.Home.RowArticle

@Composable
fun FavoritesView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()) {

    val viewModel : FavotritesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    FavotritesViewContent(modifier = modifier,
        navController = navController,
        uiState = uiState)

    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }
}

@Composable
fun  FavotritesViewContent(modifier: Modifier = Modifier,
                           navController: NavController = rememberNavController(),
                           uiState: ArticlesState) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        if (uiState.isLoading) {
            Text("Loading articles...")
        }
        else if (uiState.error != null) {
            Text("Error: ${uiState.error}")
        }
        else if (uiState.articles.isEmpty()) {
            Text("No articles found!")
        }else{
            LazyColumn(modifier = modifier
                .fillMaxSize()) {
                itemsIndexed(
                    items = uiState.articles,
                ){ index, article ->
                    RowArticle(
                        modifier = Modifier
                            .clickable {
                                Log.d("dailynews",article.url ?:"none")
                                navController.navigate(
                                    Screen.ArticleDetail.route
                                        .replace("{article}", article.toJsonString())
                                )
                            },
                        article = article)
                }
            }
        }
    }

}