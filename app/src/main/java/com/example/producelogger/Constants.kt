package com.example.producelogger

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Constants {
    companion object {
//        const val USE_TEST_DATA: Boolean = true
        const val REQUIRE_PASSWORD: Boolean = true
        const val PASSWORD: String = "isidore"
        const val API_KEY =
            "39dW0N9yjQhrpvRd2wfoOWioC2lm2RiT3UnvyyKG6W_kWeu5QXvxYZenL4stzZhUO4RipdLwlMwO97ouTjr8hcIHYFSaV2Wqm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnGS7vTvxr0kmQbYEHA6Yve_sPD_vCOY2JFjyq7-MTWxHaJKTQVa8ImEXmV4IckMg-4LpHhbw1acg_lOxPmEE-VvCV8zBOEBW1Q"
        const val LIB_ID = "M_guRrXy4yBQDxFBZiKONa_e716StSLTv"
        const val DEPLOYMENT_ID = "AKfycbwEB3sanbP-y2DDzhtSPWFsg5Q6BT5mQf1QkAuSQqvs--At5osYZgSrBP6zvXTvlsRJ"

        // Constant holding the base URL for the API.
        const val SHEET_BASE_URL = "https://script.googleusercontent.com/macros/"
        const val SHEET_BASE_URL_POST = "https://script.google.com/macros/s/$DEPLOYMENT_ID/"

        // Creates a Moshi object and adds a factory for Kotlin objects to it.
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // Adds a factory for Kotlin objects.
            .build() // Builds the Moshi object.

        // Creates a Retrofit object for making network requests.
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi).withNullSerialization().asLenient()) // Adds a converter factory for serializing and deserializing objects.
            .baseUrl(SHEET_BASE_URL) // Sets the base URL for the Retrofit object.
            .build() // Builds the Retrofit object.
        // Creates a Retrofit object for making network requests.
        val retrofitPost = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi)) // Adds a converter factory for serializing and deserializing objects.
            .baseUrl(SHEET_BASE_URL_POST) // Sets the base URL for the Retrofit object.
            .build() // Builds the Retrofit object.
    }
}