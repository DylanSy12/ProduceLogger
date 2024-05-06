package com.example.producelogger

/**
 * Data class representing a harvest with various attributes.
 *
 * @property data The date the harvest was done on.
 * @property item The item that was harvested.
 * @property weight The weight of the harvest.
 */
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