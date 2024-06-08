package com.example.producelogger


import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/** A class that manages updates to the data */
class RefreshManager {
//    private val listeners = mutableMapOf<String, () -> Unit>()
    /**
     * A function to start a coroutine for timed refreshing of the data
     *
     * @param scope The coroutine's [scope][CoroutineScope]
     * @param viewModel The [ViewModel] for managing [Harvest] data
     */
    fun start(scope: CoroutineScope, viewModel: HarvestViewModel) {
        tickerFlow(Constants.REFRESH_INTERVAL.seconds, Constants.REFRESH_INTERVAL.seconds).onEach {
            Log.d("data-refresh", "tick")
            try {
                viewModel.fetchHarvests()
                Log.i("data-refresh", "Fetched data from sheet successfully")
//                refresh()
            } catch (e: Throwable) {
                Log.e("data-refresh", "Error fetching data $it")
            }
        }.launchIn(scope)
    }
//    /**
//     * Adds a refresh listener.
//     */
//    fun addRefreshListener(listener: () -> Unit): String {
//        val id = UUID.randomUUID().toString()
//        listeners[id] = listener
//        Log.d("data-refresh", "Added id: $id")
//        return id
//    }
//    /**
//     * Removes a listener.
//     */
//    fun removeRefreshListener(id: String? = null) {
//        if (listeners.containsKey(id)) listeners.remove(id)
//        Log.d("data-refresh", "Destroyed id: $id")
//    }
//    /**
//     * Function that will go through each listener and refresh.
//     */
//    fun refresh() {
//        listeners.forEach {
//            it.value.invoke()
//            Log.d("data-refresh", "refreshed: ${it.key}")
//        }
//    }
//    /**
//     * Remove all listeners.
//     */
//    fun removeAllListeners() = listeners.clear()
//    /**
//     * Specifically refreshes one listener.
//     */
//    fun refresh(id: String) = listeners[id]?.let { it() }
}

fun tickerFlow(period: Duration, initialDelay: Duration = Duration.ZERO) = flow {
    delay(initialDelay)
    while (true) {
        emit(Unit)
        delay(period)
    }
}