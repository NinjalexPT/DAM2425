package com.example.gametest

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun GameScreenView(){

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val density = configuration.densityDpi / 160

    val screenWidhtPx = screenWidth * density
    val screenHeightPx = screenHeight * density


    AndroidView(factory = { context ->
        GameView(context = context, width = screenWidhtPx.toInt(), height = screenHeightPx.toInt())
    },
    update = {it.resume()}
    )
}