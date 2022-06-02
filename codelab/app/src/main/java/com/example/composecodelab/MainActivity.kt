package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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
    var onboardingState= remember { mutableStateOf(true)}
    if(onboardingState.value){
        onboardingScreen(onContinueClicked = {onboardingState.value = !onboardingState.value})
    }
    else{
        listScreen()
    }
}


@Composable
fun onboardingScreen(onContinueClicked : () -> Unit){
    Surface{
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Text(text = "welcome to basic codelabs")
            Button(modifier = Modifier.padding(10.dp), onClick = onContinueClicked){
                Text("Continue")
            }
        }
    }
}

@Composable
fun listScreen() {
    Surface{
        LazyColumn{
            items(items = List(100){it}){name ->
                listItem(name.toString())
            }
        }
    }
}

@Composable
fun listItem(s : String){
    var expanded by remember { mutableStateOf(false)}
    val padding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(color = Color.LightGray,modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 6.dp)){
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = padding.coerceAtLeast(0.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(text = s)
            OutlinedButton(modifier = Modifier.padding(10.dp),onClick = {expanded = !expanded} ){
                Text("Button")
            }
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

@Preview(showBackground = true)
@Composable
fun Preview() {
    MaterialTheme {
       listScreen()
    }
}