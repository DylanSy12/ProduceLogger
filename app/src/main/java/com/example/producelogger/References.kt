package com.example.producelogger

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

///**
// * [Database] that stores [Harvest]s locally
// */
//lateinit var database: Database

/**
 * [List] that stores the recorded [Harvest]s, gets from the [database]
 */
var harvestList: List<Harvest> by mutableStateOf(listOf())

/**
 * For deciding what to sort [harvestList] by (default is date)
 * D -> sort by [date][Harvest.date]
 * I -> sort by [item][Harvest.item]
 * W -> sort by [weight][Harvest.weight]
 */
var sortBy by mutableStateOf("D")

/**
 * For deciding sort order of [harvestList] (default is descending)
 * true -> descending
 * false -> ascending
 */
var sortOrder by mutableStateOf(true)