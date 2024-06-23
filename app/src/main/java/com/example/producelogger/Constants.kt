package com.example.producelogger

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

class Constants {
    companion object {
        /** Whether or not a password is necessary to record a [Harvest] */
        const val REQUIRE_PASSWORD: Boolean = true
        /** The password for allowing users to record [Harvests][Harvest] */
        const val PASSWORD: String = "isidore"
        
        /** The interval, in seconds, between automatic data refreshes */
        const val REFRESH_INTERVAL = 150
        
        // API information
        /** The User Content Key of the API */
        const val USER_CONTENT_KEY =
            "39dW0N9yjQhrpvRd2wfoOWioC2lm2RiT3UnvyyKG6W_kWeu5QXvxYZenL4stzZhUO4RipdLwlMwO97ouTjr8hcIHYFSaV2Wqm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnGS7vTvxr0kmQbYEHA6Yve_sPD_vCOY2JFjyq7-MTWxHaJKTQVa8ImEXmV4IckMg-4LpHhbw1acg_lOxPmEE-VvCV8zBOEBW1Q"
        /** The Library ID of the API */
        const val LIB_ID = "M_guRrXy4yBQDxFBZiKONa_e716StSLTv"
        /** The Deployment ID of the API */
        private const val DEPLOYMENT_ID = "AKfycbwEB3sanbP-y2DDzhtSPWFsg5Q6BT5mQf1QkAuSQqvs--At5osYZgSrBP6zvXTvlsRJ"

        /** The Base URL for making [GET] requests to the API */
        private const val SHEET_BASE_URL_GET = "https://script.googleusercontent.com/macros/"
        /** The Base URL for making [POST] requests to the API */
        private const val SHEET_BASE_URL_POST = "https://script.google.com/macros/s/$DEPLOYMENT_ID/"

        // Network request builder
        /** A [Moshi] object with a [Factory][JsonAdapter.Factory] for Kotlin objects */
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // Adds a factory for Kotlin objects
            .build() // Builds the Moshi object

        /** The [Retrofit] object for making network [GET] requests to the API */
        val retrofitGET: Retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi)) // Adds a converter factory for serializing and deserializing objects
            .baseUrl(SHEET_BASE_URL_GET) // Sets the base URL for the Retrofit object
            .build() // Builds the Retrofit object
        /** The [Retrofit] object for making network [POST] requests to the API */
        val retrofitPOST: Retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi)) // Adds a converter factory for serializing and deserializing objects
            .baseUrl(SHEET_BASE_URL_POST) // Sets the base URL for the Retrofit object
            .build() // Builds the Retrofit object
    }
}