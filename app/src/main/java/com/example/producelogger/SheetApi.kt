package com.example.producelogger

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import java.net.Inet4Address

/**
 * This interface defines the endpoints for the Google Sheets API service.
 */
interface SheetApiService {
    /**
     * Sends a [GET] request to fetch the [Harvest] data from the Google Sheets API.
     *
     * @param apiKeyGoogle The Google API key for authentication.
     * @param libraryId The ID of the library in Google Sheets.
     * @return A [HarvestResponse] object containing the list of harvests fetched from the API.
     */
    @GET("echo")
    suspend fun getHarvests(
        @Query("user_content_key") apiKeyGoogle: String,
        @Query("lib") libraryId: String,
    ): HarvestResponse

    /**
     * Sends a [POST] request to add a new [Harvest] data to the Google Sheets.
     *
     * @param apiKeyGoogle The Google API key for authentication.
     * @param libraryId The ID of the library in Google Sheets.
     * @param harvest The [Harvest] object containing the data to be added.
     * @return A [ResponseBody] object which can be used to handle the response from the API.
     */
    @POST("exec")
    suspend fun addHarvest(@Body harvest: Harvest): ResponseBody
}

/**
 * A singleton object to initialize and provide the [SheetApiService] instance.
 */
object SheetApi {
    /**
     * Lazily initializes and provides the [SheetApiService] instance.
     */
    val retrofitService: SheetApiService by lazy {
        Constants.retrofit.create(SheetApiService::class.java)
    }
    val retrofitServicePost: SheetApiService by lazy {
        Constants.retrofitPost.create(SheetApiService::class.java)
    }
}