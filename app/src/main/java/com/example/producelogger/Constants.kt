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
        const val API_KEY =
            "WwypaSIk_7PIKmD7cXGD22H7ctUGuji21woAlfOsFT1ZDgrxIueXsQfwCELEeu0hz7sTFe7yX4e3EFpTIJo45uHfi0zLlAnzOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKDF_RqZIMLv8_2APE1J3Id3GqVCQ9zTauVc0Xa4cEc0I0zJreJAMUonGgGvmTMMw69YMOV5WltlvA-9A4NKlTV-erjJLbG9LBSOiWvrOIh6QanmvDeJRYj-6EL-KjY7hyN1VzQcfrf7rg"
        const val LIB_ID = "MqtWDm5rwXQEwGWHL5ZJa2YMk3ZfA5LIg"

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