package com.example.producelogger

import android.util.Log
import okhttp3.ResponseBody

/** A repository class for managing data operations for [Harvests][Harvest] */
class HarvestRepository {

    /**
     * Fetches a list of [Harvests][Harvest] from the Google Sheet API
     *
     * @return A [HarvestResponse] object containing a list of [Harvests][Harvest]
     */
    suspend fun fetchHarvests(): HarvestResponse = 
        SheetApi.retrofitServiceGET.getHarvests(Constants.USER_CONTENT_KEY, Constants.LIB_ID)

    /**
     * Adds a new [Harvest] to the Google Sheet via the API.
     *
     * @param harvest The [Harvest] object to be added to the Google Sheet
     * @return A [ResponseBody] object indicating the result of the operation
     */
    suspend fun addHarvest(harvest: Harvest): ResponseBody = 
        SheetApi.retrofitServicePOST.addHarvest(harvest)
}