package com.example.producelogger

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.producelogger.data.Database
import com.example.producelogger.data.Harvest
import com.example.producelogger.data.HarvestViewModel
import com.example.producelogger.data.RefreshManager
import com.example.producelogger.navigation.SetupNavGraph
import com.example.producelogger.ui.theme.Brown
import com.example.producelogger.ui.theme.DarkGreen
import com.example.producelogger.ui.theme.ProduceLoggerTheme

/**
 * The main [activity][ComponentActivity] of the app
 *
 * @property navController The [NavHostController] controlling which screen is displayed
 * @property viewModel The [ViewModel] for managing [Harvest] data
 * @property refreshManager The [RefreshManager] for managing automatic updates to the [harvestList]
 */
class MainActivity : ComponentActivity() {

    /** The [NavHostController] controlling which screen is displayed */
    private lateinit var navController: NavHostController

    /** The [ViewModel] for managing [Harvest] data */
    private lateinit var viewModel: HarvestViewModel

    companion object {
        /** The [RefreshManager] for managing automatic updates to the [harvestList] */
        val refreshManager = RefreshManager()
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sets up viewModel
        viewModel = ViewModelProvider(this)[HarvestViewModel::class.java]

        // Whenever the data stored in viewModel is updated, updates harvestList
        viewModel.harvests.observe(this) { harvests ->
            if (harvests.isNotEmpty()) {
//                Log.e("Updating", "Has Data")
                harvestList = harvests as ArrayList<Harvest>
                database = Database(this)
                val tempList: ArrayList<Harvest> = database.harvests
                for (harvest in tempList) {
                    if (harvest !in harvestList) {
//                        Log.e("Harvest Not In List", harvest.toString())
                        viewModel.addHarvest(harvest)
//                        (harvestList as ArrayList<Harvest>).add(harvest)
                    }
                }
//                viewModel.fetchHarvests()
                database.updateHarvests(harvestList as ArrayList<Harvest>)
            }
            else {
//                Log.e("Updating", "Empty")
                database = Database(this)
                harvestList = database.harvests
            }
        }

        // Initial fetch from the Google Sheet
        viewModel.fetchHarvests()

        // Starts the automatic refresh
        refreshManager.start(lifecycleScope, viewModel)

        // Sets the screen's content
        setContent {
            ProduceLoggerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        // Header for the app
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.largeTopAppBarColors(
                                    containerColor = DarkGreen,
                                    titleContentColor = Brown,
                                ),
                                title = {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
                                    ) {
                                        Text(
                                            text = "Produce Logger",
                                            style = TextStyle(fontSize = 50.sp)
                                        )
                                    }
                                }
                            )
                        }
                    ) {
                        // Displays the screen
                        navController = rememberNavController()
                        SetupNavGraph(navController = navController, viewModel = viewModel)
                    }
                }
            }
        }
    }
}