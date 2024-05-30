package com.example.producelogger

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * A [ViewModel] subclass for managing [Harvest] data.
 *
 * This [ViewModel] interacts with [HarvestRepository] to fetch and add [Harvest] data,
 * and exposes the data to the UI layer via [LiveData].
 *
 * @param repository The repository instance for fetching and adding [Harvest] data.
 */
class HarvestViewModel(private val repository: HarvestRepository = HarvestRepository()) :
    ViewModel() {

    /** A private [MutableLiveData] that holds a list of [Harvest] objects. */
    private val _harvests = MutableLiveData<List<Harvest>>()

    /** A public [LiveData] for exposing the [Harvest] list to the UI layer. */
    val harvests: LiveData<List<Harvest>> get() = _harvests

    /**
     * Fetches [Harvest] data from the repository.
     *
     * This method is a coroutine, which allows it to perform long-running operations
     * such as network requests or disk access without blocking the main thread.
     *
     * @param apiKeyGoogle The API key for accessing the Google Sheet.
     * @param libraryId The library ID for accessing the Google Sheet.
     */
    fun fetchHarvests(apiKeyGoogle: String, libraryId: String) {
        // Launch a coroutine in ViewModelScope
        viewModelScope.launch {
            // Fetch the harvest data from the repository.
            val harvestResponse =
                repository.fetchHarvests(apiKeyGoogle = apiKeyGoogle, libraryId = libraryId)
            Log.e("ResponseFetch", harvestResponse.toString())
            // If the response is non-null, update _harvests LiveData.
//            var harvestsList = mutableListOf<Harvest>()
//            harvestResponse.data.forEach {harvest ->
//                harvestsList.add(Harvest(harvest["date"].toString(), harvest["item"].toString(), harvest["weight"].toString()))
//            }
//            _harvests.value = harvestsList
            _harvests.value = harvestResponse.data
        }
    }

    /**
     * Adds a new [Harvest] to the repository.
     *
     * This method is a coroutine, which allows it to perform long-running operations
     * such as network requests or disk access without blocking the main thread.
     *
     * @param apiKeyGoogle The API key for accessing the Google Sheet.
     * @param libraryId The library ID for accessing the Google Sheet.
     * @param harvest The [Harvest] data to be added.
     */
//    fun addHarvest(apiKeyGoogle: String, libraryId: String, harvest: Harvest) {
//        // Launch a coroutine in ViewModelScope
//        viewModelScope.launch {
//            // Add the new Harvest to the repository.
//            repository.addHarvest(apiKeyGoogle, libraryId, harvest)
//        }
//    }
}