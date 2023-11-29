package com.example.producelogger

sealed class Screen(val route: String) {
    object HarvestLog: Screen(route = "harvest_log")
    object HarvestRecorder: Screen(route = "harvest_recorder")
}
