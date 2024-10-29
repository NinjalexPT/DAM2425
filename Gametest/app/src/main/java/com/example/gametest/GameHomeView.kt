package com.example.gametest


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun GameHomeView(modifier: Modifier = Modifier,
                 onPlayClick: () -> Unit = {}){


    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter){


        Image(painter = painterResource(id = R.drawable.splash)
            ,contentDescription = ""
            ,modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)


        Column(modifier = Modifier.padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally){


            Image(painter = painterResource(id = R.drawable.playnow),
                    contentDescription = "",
                    modifier = Modifier.height(80.dp).width(400.dp)
                        .clickable {
                            onPlayClick()
                        },
                contentScale = ContentScale.FillBounds)

            Spacer(modifier = Modifier.padding(10.dp))

            Image(painter = painterResource(id = R.drawable.highscore),
                    contentDescription = "",
                    modifier = Modifier.height(60.dp).width(300.dp),
                contentScale = ContentScale.FillBounds)
        }
    }


}

@Preview
@Composable
fun GameHomeViewPreview(){
    GameHomeView()
}