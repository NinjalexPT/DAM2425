package com.example.gametest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun GameOverView(modifier: Modifier = Modifier,
                 onResumeClick: () -> Unit = {}){

    Box(modifier = modifier.fillMaxSize().clickable { onResumeClick() },
        contentAlignment = Alignment.Center){
        Text(text = "Game Over",
            fontSize = 100.sp)
    }
    Box(modifier = modifier.fillMaxSize(),contentAlignment = Alignment.TopCenter){
        Text(text = "Score = dont know",fontSize = 50.sp)

    }

}

@Preview(showBackground = true)
@Composable
fun GameOverViewPreview() {
    GameOverView()
}