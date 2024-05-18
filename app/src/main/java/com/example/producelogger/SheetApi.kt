package com.example.producelogger

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.net.Inet4Address

class Ipv4OnlyDns : Dns {
    override fun lookup(hostname: String) = Dns.SYSTEM.lookup(hostname).sortedBy { it !is Inet4Address }
}

// Creates a client for the http request
val client = HttpClient(OkHttp) {

    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
    engine {
        preconfigured = OkHttpClient.Builder().dns(Ipv4OnlyDns()).build()
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("Ktor", message)
            }
        }
        level = LogLevel.ALL
    }
    // Sets the timeout to be 60 seconds
    install(HttpTimeout) {
        requestTimeoutMillis = 60 * 1000//900 * 1000 for champs
        connectTimeoutMillis = 60 * 1000//900 * 1000 for champs
        socketTimeoutMillis = 60 * 1000//900 * 1000 for champs
    }
}

object ProduceApi {
    suspend fun getHarvests(apiKeyGoogle: String, libraryId: String): HarvestResponse = client.get("${Constants.SHEET_BASE_URL}echo?") {
        parameter("user_content_key", apiKeyGoogle)
        parameter("lib", libraryId)
    }.body()

    suspend fun addHarvest(apiKeyGoogle: String, libraryId: String, harvest: Harvest) =
        client.post("${Constants.SHEET_BASE_URL}exec?") {
            parameter("user_content_key", apiKeyGoogle)
            parameter("lib", libraryId)
            contentType(ContentType.Application.Json)
            setBody(harvest)
        }
}

/**
 * This interface defines the endpoints for the Google Sheets API service.
 */
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
    /**
     * Sends a [GET] request to fetch the [Harvest] data from the Google Sheets API.
     *
     * @param apiKeyGoogle The Google API key for authentication.
     * @param libraryId The ID of the library in Google Sheets.
     * @return A [HarvestResponse] object containing the list of harvests fetched from the API.
     */
    @GET("echo?")
    suspend fun getHarvests(
        @Query("user_content_key") apiKeyGoogle: String,
        @Query("lib") libraryId: String
    ): HarvestResponse

    /**
     * Sends a [POST] request to add a new [Harvest] data to the Google Sheets.
     *
     * @param apiKeyGoogle The Google API key for authentication.
     * @param libraryId The ID of the library in Google Sheets.
     * @param harvest The [Harvest] object containing the data to be added.
     * @return A [ResponseBody] object which can be used to handle the response from the API.
     */
    /**
     * Sends a [POST] request to add a new [Harvest] data to the Google Sheets.
     *
     * @param apiKeyGoogle The Google API key for authentication.
     * @param libraryId The ID of the library in Google Sheets.
     * @param harvest The [Harvest] object containing the data to be added.
     * @return A [ResponseBody] object which can be used to handle the response from the API.
     */
    @POST("echo?/")
    suspend fun addHarvest(
        @Query("user_content_key") apiKeyGoogle: String,
        @Query("lib") libraryId: String,

        @Body harvest: Harvest
    ): ResponseBody
}

/**
 * A singleton object to initialize and provide the [SheetApiService] instance.
 */
/**
 * A singleton object to initialize and provide the [SheetApiService] instance.
 */
object SheetApi {
    /**
     * Lazily initializes and provides the [SheetApiService] instance.
     */
    /**
     * Lazily initializes and provides the [SheetApiService] instance.
     */
    val retrofitService: SheetApiService by lazy {
        Constants.retrofit.create(SheetApiService::class.java)
    }
}