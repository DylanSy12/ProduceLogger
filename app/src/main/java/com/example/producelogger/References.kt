package com.example.producelogger

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// List that stores the recorded harvests
var harvestList: ArrayList<Harvest> = ArrayList()

/**
 * Sort by variable (default is date)
 * D -> sort by date
 * I -> sort by item
 * W -> sort by weight
 */
var sortBy by mutableStateOf("D")

/**
 * Sort order variable (default is descending)
 * true -> descending
 * false -> ascending
 */
var sortOrder by mutableStateOf(true)