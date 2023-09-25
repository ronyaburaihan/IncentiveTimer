package com.englesoft.incentivetimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.englesoft.incentivetimer.ui.theme.IncentiveTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IncentiveTimerTheme {

            }
        }
    }
}

@Composable
private fun ScreenContent() {
    val navController = rememberNavController()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IncentiveTimerTheme {
        ScreenContent()
    }
}