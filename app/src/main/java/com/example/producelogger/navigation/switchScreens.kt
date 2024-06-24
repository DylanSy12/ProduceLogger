package com.example.producelogger.navigation

import androidx.navigation.NavController
import com.example.producelogger.data.HarvestViewModel

/**
 * Switches the screen currently being displayed
 *
 * @param navController The [NavController] controlling which screen is displayed
 * @param route The screen that it will switch to
 */
fun switchScreens(navController: NavController, route: String, viewModel: HarvestViewModel) {
    navController.navigate(route) {
        popUpTo(route) {
            saveState = true
            inclusive = true
        }
    }
    if (route != Screen.HarvestRecorder.route) viewModel.fetchHarvests()
}