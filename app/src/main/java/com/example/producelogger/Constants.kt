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
        const val API_KEY = "525wjZMzQFNtXXrno2fcsDoz9pH6OL9YQ75IUK8cZBQn71Kmf_3-VE3EVPQvz1jI_-JHagxTsvZWuPh3UbSTFtF61rhXFWZGOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKAkMb5gKIdxpac2pVaclM0mFBrT160VvrmmRKz89z2PoGhoU368kDEiN_Cpk25Z67RVVo57uyndCnDSctqYRMdUqAXeNk-ALde5PVSpG6OuGA2_KXcAzpc__T-8_kMLBxNcy6pr-Oj0FQ"
        const val LIB_ID = "MWoR8V1UsFe4rknSrv5_OkqOZ8s91DZXc"

        // Constant holding the base URL for the API.
        const val SHEET_BASE_URL = ""
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