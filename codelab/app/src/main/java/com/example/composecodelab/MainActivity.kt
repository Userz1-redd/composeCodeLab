package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composecodelab.ui.theme.ComposeCodeLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MaterialTheme {
                // A surface container using the 'background' color from the theme
               MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(backgroundColor = Color.White, topBar = {MyAppBar()}) {
            MyListView()
        }
    }
}

@Composable
fun MyAppBar(){
    TopAppBar(elevation = 10.dp){
        Text(text = "리스트입니다",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black
            )
    }
}


@Composable
fun MyListView(){
    LazyColumn(){
        items(100){
            MyListItem()
        }
    }
}

@Composable
fun MyListItem(){
    Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://example.com/image.jpg")
                .size(10)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = stringResource(R.string.description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape),
        )

        Column(){
            Text(text = "MyUser",modifier = Modifier.padding(10.dp))
            Text(text = "Hello",modifier = Modifier.padding(10.dp))
        }


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
       MyApp()
    }
}