package com.example.producelogger.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.producelogger.reformatDate
import kotlinx.coroutines.launch

/**
 * A [ViewModel] subclass for managing [Harvest] data
 *
 * This [ViewModel] interacts with [HarvestRepository] to fetch and add [Harvest] data,
 * and exposes the data to the UI layer via [LiveData].
 *
 * @param repository The repository instance for fetching and adding [Harvest] data
 */
class HarvestViewModel(private val repository: HarvestRepository = HarvestRepository()) :
    ViewModel() {

    /** A private [MutableLiveData] that holds a [List] of [Harvests][Harvest] */
    private val _harvests = MutableLiveData<List<Harvest>>()

    /** A public [LiveData] for exposing the [Harvest] list to the UI layer */
    val harvests: LiveData<List<Harvest>> get() = _harvests

    /**
     * Fetches [Harvest] data from the [repository]
     *
     * This method is a coroutine, which allows it to perform long-running operations
     * such as network requests or disk access without blocking the main thread.
     */
    fun fetchHarvests() {
        // Launch a coroutine in ViewModelScope
        viewModelScope.launch/*(Dispatchers.Unconfined)*/ {
            // Fetch the harvest data from the repository
            val harvestResponse = repository.fetchHarvests()
            // If the response is non-null, update _harvests LiveData
//            _harvests.postValue(repository.fetchHarvests().data)
            for (harvest in harvestResponse.data) {
                harvest.date = reformatDate(harvest.date)
            }
            _harvests.value = harvestResponse.data
        }
    }

    /**
     * Adds a new [Harvest] to the [repository]
     *
     * This method is a coroutine, which allows it to perform long-running operations
     * such as network requests or disk access without blocking the main thread.
     *
     * @param harvest The [Harvest] data to be added
     */
    fun addHarvest(harvest: Harvest) {
        // Launch a coroutine in ViewModelScope
        viewModelScope.launch {
            // Add the new Harvest to the repository
            repository.addHarvest(harvest)
        }
    }
}