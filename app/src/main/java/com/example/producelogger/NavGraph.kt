package com.example.producelogger

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Screens
@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: HarvestViewModel) {
    NavHost(navController = navController, startDestination = Screen.HarvestLog.route) {
        composable(route = Screen.HarvestLog.route) { HarvestLogComposable(navController = navController, viewModel = viewModel) }
        composable(route = Screen.HarvestRecorder.route) { HarvestRecorderComposable(navController = navController, viewModel = viewModel) }
    }
}