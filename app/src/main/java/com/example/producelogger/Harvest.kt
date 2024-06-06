package com.example.producelogger

import kotlinx.serialization.Serializable

/**
 * Data class representing a harvest with various attributes.
 *
 * @property date The date the harvest was done on.
 * @property item The item that was harvested.
 * @property weight The weight of the harvest.
 */
@Serializable
data class Harvest(
    var date: String = "",
    var item: String = "",
    var weight: String = ""
)

/**
 * Data class representing a response from a Harvest API.
 *
 * @property data A list of Harvest objects.
 */
data class HarvestResponse(val data: List<Harvest>)