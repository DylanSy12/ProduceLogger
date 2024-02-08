package com.example.producelogger

// Screens routing
sealed class Screen(val route: String) {
    object HarvestLog: Screen(route = "harvest_log")
    object HarvestRecorder: Screen(route = "harvest_recorder")
}