package com.example.producelogger

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.producelogger.ui.theme.ProduceLoggerTheme

@Composable
fun HarvestRecorderComposable(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

    }
}

@Preview(showBackground = true)
@Composable
fun screenPreviewLogger() {
    ProduceLoggerTheme {
        HarvestRecorderComposable(navController = rememberNavController())
    }
}