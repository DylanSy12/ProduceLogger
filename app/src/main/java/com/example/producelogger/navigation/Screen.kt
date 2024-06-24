package com.example.producelogger.navigation

import com.example.producelogger.composables.screens.*

/**
 * A sealed class storing the routing for the screens
 *
 * @property HarvestLog The route corresponding to the [HarvestLog][HarvestLogComposable] screen
 * @property HarvestRecorder The route corresponding to the [HarvestRecorder][HarvestRecorderComposable] screen
 */
sealed class Screen(val route: String) {

    /** Route for the [HarvestLog][HarvestLogComposable] screen */
    object HarvestLog: Screen(route = "harvest_log")

    /** Route for the [HarvestRecorder][HarvestRecorderComposable] screen */
    object HarvestRecorder: Screen(route = "harvest_recorder")
}