package com.example.newsapp


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview

import com.example.newsapp.module.Article
import com.example.newsapp.module.RowArticle
import com.example.newsapp.ui.theme.NewsappTheme
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

import java.util.Date


@Composable
fun NewsApp(modifier: Modifier = Modifier) {

    var articles by remember{ mutableStateOf(listOf<Article>()) }

    LazyColumn {
        itemsIndexed(
            items = articles,
        ){
                index, article ->
            RowArticle(article = article)
        }

    }

    LaunchedEffect(Unit) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=28529f52739c40d299dbe841c19dd1d7")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val articlesResult = arrayListOf<Article>()
                    val result = response.body!!.string()
                    val jsonResult = JSONObject(result)
                    val status = jsonResult.getString("status")
                    if (status == "ok") {
                        val articlesJson = jsonResult.getJSONArray("articles")
                        for (index in 0 until articlesJson.length()) {
                            val articleJson = articlesJson.getJSONObject(index)
                            val article = Article.fromjson(articleJson)
                            articlesResult.add(article)
                        }
                    }
                    articles = articlesResult

                }
            }
        })
    }
}



@Preview(showBackground = true)
@Composable
fun NewsAppPreview() {
    NewsappTheme {
        NewsApp()
    }
}