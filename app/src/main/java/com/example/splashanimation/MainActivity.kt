package com.example.splashanimation

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splashanimation.ui.theme.SplashAnimationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("main_screen") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "this is another screen")
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { // 1ta animation yaratib olamiz
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) { // true berish sababi faqat 1marotaba chiqishi un animationni
        scale.animateTo( // featurelari animationni
            targetValue = 0.3f,
            animationSpec = tween(500, easing = { OvershootInterpolator(0.5f).getInterpolation(it) })


        )
        delay(1000L)
        navController.navigate("main_screen")
    }




    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.scale(scale.value).fillMaxSize()
        )
    }
}