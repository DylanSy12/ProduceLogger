package com.example.producelogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.producelogger.ui.theme.ProduceLoggerTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProduceLoggerTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
                // A surface container using the 'background' color from the theme
            }
        }
    }
}

