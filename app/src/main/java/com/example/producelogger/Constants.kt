package com.example.producelogger

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Constants {
    companion object {
        const val USE_TEST_DATA: Boolean = true
        const val REQUIRE_PASSWORD: Boolean = false
        const val PASSWORD: String = "password"
        const val API_KEY = "vD6yWVJ7lS9CX4kMA6ziJ30ox5dTOXkz9lPzw9JAbiKZZdwFoYODdrm3I_b_TKnvh3setGRsdghsH97WmTKI1ilsBs1o-tBAm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnC-gqCFuW7cjxNNCk1SkjX1GGfrK9hiVio-K3l_qlNEVGKbUWZtafVfFsHmAd8M4YwW9Erp8hUeGQ_YN6KItOtPa0pNqAUED1Q"
        const val LIB_ID = "MWoR8V1UsFe4rknSrv5_OkqOZ8s91DZXc"

        // Constant holding the base URL for the API.
        const val SHEET_BASE_URL = "https://script.googleusercontent.com/macros/"
        // Creates a Moshi object and adds a factory for Kotlin objects to it.
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // Adds a factory for Kotlin objects.
            .build() // Builds the Moshi object.
        // Creates a Retrofit object for making network requests.
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi)) // Adds a converter factory for serializing and deserializing objects.
            .baseUrl(SHEET_BASE_URL) // Sets the base URL for the Retrofit object.
            .build() // Builds the Retrofit object.
    }
}