package com.example.producelogger.data

import com.example.producelogger.Constants
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/** An interface defining the endpoints for the Google Sheets API service */
interface SheetApiService {

    /**
     * Sends a [GET] request to fetch the [Harvest] data from the Google Sheets API
     *
     * @param apiKeyGoogle The Google API key for authentication
     * @param libraryId The ID of the library in Google Sheets
     * @return A [HarvestResponse] object containing the [List] of [Harvests][Harvest] fetched from the API
     */
    @GET("echo")
    suspend fun getHarvests(
        @Query("user_content_key") apiKeyGoogle: String,
        @Query("lib") libraryId: String,
    ): HarvestResponse

    /**
     * Sends a [POST] request to add new [Harvest] data to the Google Sheets
     *
     * @param harvest The [Harvest] object containing the [Harvest] data to be added
     * @return A [ResponseBody] object which can be used to handle the response from the API
     */
    @POST("exec")
    suspend fun addHarvest(@Body harvest: Harvest): ResponseBody
}

/** A singleton object to initialize and provide the [SheetApiService] instances */
object SheetApi {

    /** Lazily initializes and provides the [SheetApiService] instance for [GET] requests */
    val retrofitServiceGET: SheetApiService by lazy {
        Constants.retrofitGET.create(SheetApiService::class.java)
    }

    /** Lazily initializes and provides the [SheetApiService] instance for [POST] requests */
    val retrofitServicePOST: SheetApiService by lazy {
        Constants.retrofitPOST.create(SheetApiService::class.java)
    }
}