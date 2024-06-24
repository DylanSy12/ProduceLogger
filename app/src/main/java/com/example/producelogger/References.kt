package com.example.producelogger

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.producelogger.data.Database
import com.example.producelogger.data.Harvest

/**
 * [Database] that stores [Harvest]s locally
 */
lateinit var database: Database

/** A [List] that stores the recorded [Harvests][Harvest] fetched from the Google Sheets API */
var harvestList: List<Harvest> by mutableStateOf(listOf())

/**
 * Determines how the [harvestList] is sorted
 *
 * D -> Sort chronologically by [date][Harvest.date] (Default)
 *
 * I -> Sort alphabetically by [item][Harvest.item]
 *
 * W -> Sort numerically by [weight][Harvest.weight]
 */
var sortBy by mutableStateOf("D")

/**
 * Determines the sort order of the [harvestList]
 *
 * true -> Sort in descending order (Default)
 *
 * false -> Sort in ascending order
 */
var sortOrder by mutableStateOf(true)