package com.example.producelogger

import android.util.Log
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.EMPTY_RESPONSE

/** A repository class for managing data operations for [Harvests][Harvest] */
class HarvestRepository {

    /**
     * Fetches a list of [Harvests][Harvest] from the Google Sheet API
     *
     * @return A [HarvestResponse] object containing a list of [Harvests][Harvest]
     */
    suspend fun fetchHarvests(): HarvestResponse =
        try {
            SheetApi.retrofitServiceGET.getHarvests(Constants.USER_CONTENT_KEY, Constants.LIB_ID)
        } catch(e: Exception) {
            HarvestResponse(listOf())
        }

    /**
     * Adds a new [Harvest] to the Google Sheet via the API.
     *
     * @param harvest The [Harvest] object to be added to the Google Sheet
     * @return A [ResponseBody] object indicating the result of the operation
     */
    suspend fun addHarvest(harvest: Harvest): ResponseBody =
        try {
            SheetApi.retrofitServicePOST.addHarvest(harvest)
        } catch (e: Exception) {
            EMPTY_RESPONSE
        }
}