package com.example.myshoppinglist.ui.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myshoppinglist.R
import com.example.myshoppinglist.Screen
import com.example.myshoppinglist.ui.theme.MyShoppingListTheme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ListListview(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {

    val viewModel: ListListviewmodel = viewModel()
    val state = viewModel.state.value
    fun LogoutClick(navController: NavController) {
        FirebaseAuth.getInstance().signOut()
        navController.navigate(Screen.Login.route)
    }


    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {

        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(
                items = state.listItemsList
            ) { index, item ->

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            navController.navigate(
                                Screen.ListItems.route.replace("{listId}", item.DocId!!)
                            )
                        },
                    text = item.name ?: ""
                )

            }
        }

        Button(
            modifier = Modifier
                .padding(16.dp)
                .size(64.dp),
            onClick = {
                navController.navigate(Screen.AddList.route)
            }) {
            Image(
                modifier = Modifier
                    .scale(2.0f)
                    .size(64.dp),
                colorFilter = ColorFilter.tint(Color.White),
                painter = painterResource(R.drawable.baseline_add_24),
                contentDescription = "add"
            )
        }

    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Button(
            modifier = Modifier
                .padding(16.dp)
                .size(64.dp),
            onClick = { LogoutClick(navController) }) {
            Image(
                modifier = Modifier.scale(2.0f).size(64.dp),
                colorFilter = ColorFilter.tint(Color.White),
                painter = painterResource(R.drawable.baseline_exit_to_app_24),
                contentDescription = "logout"
            )


        }

        LaunchedEffect(key1 = true) {
            viewModel.getLists()
        }

    }
}



@Preview(showBackground = true)
@Composable
fun ListListviewPreview() {
    MyShoppingListTheme {
        ListListview()
    }
}
