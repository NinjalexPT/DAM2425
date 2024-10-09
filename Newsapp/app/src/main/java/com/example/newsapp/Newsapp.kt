package com.example.newsapp

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.module.Article
import com.example.newsapp.ui.theme.NewsappTheme

import java.util.Date


@Composable
fun NewsApp(modifier: Modifier = Modifier) {
    var articles = arrayListOf(
        Article(
            "dsflsdlfk selkfwçlfmkqw",
            "description",
            "Content",
            "url",
            Date(),
            "urlToImage"
        ),
        Article(
            "WEFWEFWEFWF ",
            "description",
            "urlToImage",
            "url",
            Date(),
            "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"



        )
    )

    LazyColumn {
        itemsIndexed(
            items = articles,
        ){
                index, article ->
            Text(text = article.title!! + article.description!!)
            article.imageUrl?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "image article",
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)
                )

            }

        }

    }
}

@Composable
fun AsyncImage(model: String, contentDescription: String, modifier: Modifier) {
    
}


@Preview(showBackground = true)
@Composable
fun NewsAppPreview() {
    NewsappTheme {
        NewsApp()
    }
}