package com.example.newsapp.ui.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

import com.example.newsapp.R
import com.example.newsapp.module.Article
import com.example.newsapp.toYYYYMMDD

import com.example.newsapp.ui.theme.NewsappTheme
import java.util.Date

@Composable
fun RowArticle(modifier: Modifier = Modifier, article: Article) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), verticalAlignment = Alignment.Top) {
        article.imageUrl?.let {
            AsyncImage(
                model = it,
                contentDescription = "image article",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RectangleShape),

            )

        } ?: run {
            Image(
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp),
                painter = painterResource(id = R.drawable.baseline_domain_24),
                contentDescription = "image article"
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Column(modifier = modifier.fillMaxWidth()) {
            Text(text = article.title ?: "",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = article.description ?: "",
                style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = article.publishedDate?.toYYYYMMDD() ?: "",
                style = MaterialTheme.typography.bodySmall)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RowArticlePreview() {
    NewsappTheme {
        RowArticle(
            article = Article(
                "dsflsdlfk selkfwçlfmkqw",
                "description",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
                "url",
                Date())
        )
    }
}