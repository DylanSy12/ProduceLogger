package com.example.producelogger

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonElement
import java.io.Serial

/**
 * Data class representing a harvest with various attributes.
 *
 * @property data The date the harvest was done on.
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
@Serializable
//data class HarvestResponse(val data: List<Map<String, JsonElement>>)
data class HarvestResponse(val data: List<Harvest>)