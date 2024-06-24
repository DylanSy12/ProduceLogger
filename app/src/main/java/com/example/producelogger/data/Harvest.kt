package com.example.producelogger.data

import kotlinx.serialization.Serializable

/**
 * A data class representing a harvest with various attributes
 *
 * @property date The date the harvest was done on
 * @property item The item that was harvested
 * @property weight The weight of the harvest
 */
@Serializable
data class Harvest(
    var date: String = "",
    var item: String = "",
    var weight: String = ""
)

/**
 * A data class representing a response from a Harvest API
 *
 * @property data A [List] of [Harvests][Harvest]
 */
data class HarvestResponse(val data: List<Harvest>)