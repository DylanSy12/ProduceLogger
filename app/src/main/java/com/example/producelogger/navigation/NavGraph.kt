package com.example.producelogger.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.producelogger.data.HarvestViewModel
import com.example.producelogger.composables.screens.HarvestLogComposable
import com.example.producelogger.composables.screens.HarvestRecorderComposable
import com.example.producelogger.data.Harvest

/**
 * A [Composable] function to display the current screen
 *
 * @param navController The [NavHostController] controlling which screen is displayed
 * @param viewModel The [ViewModel] for managing [Harvest] data
 */
@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: HarvestViewModel) {
    NavHost(navController = navController, startDestination = Screen.HarvestLog.route) {
        composable(route = Screen.HarvestLog.route) { HarvestLogComposable(navController = navController, viewModel = viewModel) }
        composable(route = Screen.HarvestRecorder.route) { HarvestRecorderComposable(navController = navController, viewModel = viewModel) }
    }
}