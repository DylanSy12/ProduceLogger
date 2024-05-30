package com.example.producelogger

/**
 * A repository class for managing data operations for [Harvest]s.
 */
class HarvestRepository {
    // Define a constant for the API key.
    private val apiKeyGoogle = Constants.API_KEY

    // Define a constant for the library id.
    private val libraryId = Constants.LIB_ID

    /**
     * Fetches a list of [Harvest]s from the Google Sheet API.
     *
     * @param apiKeyGoogle The API key for accessing the Google Sheet.
     * @param libraryId The library ID for accessing the Google Sheet.
     * @return A [HarvestResponse] object containing a list of [Harvest]s or null if the request fails.
     */
    suspend fun fetchHarvests(apiKeyGoogle: String, libraryId: String): HarvestResponse {
        return SheetApi.retrofitService.getHarvests(apiKeyGoogle, libraryId)
    }

//    /**
//     * Adds a new [Harvest] to the Google Sheet via the API.
//     *
//     * @param apiKeyGoogle The API key for accessing the Google Sheet.
//     * @param libraryId The library ID for accessing the Google Sheet.
//     * @param harvest the [Harvest] object to be added to the sheet.
//     * @return A [ResponseBody] object indicating the result of the operation or null if the request fails.
//     */
//    suspend fun addHarvest(apiKeyGoogle: String, libraryId: String, harvest: Harvest): ResponseBody {
//        return SheetApi.retrofitService.addHarvest(apiKeyGoogle, libraryId, harvest)
//    }
}