package com.example.producelogger

//import com.example.producelogger.ProduceApi.getHarvests
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.producelogger.ui.theme.Brown
import com.example.producelogger.ui.theme.DarkGreen
import com.example.producelogger.ui.theme.ProduceLoggerTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private lateinit var viewModel: HarvestViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[HarvestViewModel::class.java]

        viewModel.harvests.observe(this) { harvests ->
            harvestList = harvests as ArrayList<Harvest>
        }

        viewModel.fetchHarvests(Constants.API_KEY, Constants.LIB_ID)
        // Sets screen content
        setContent {
            ProduceLoggerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        // App header
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.largeTopAppBarColors(
                                    containerColor = DarkGreen,
                                    titleContentColor = Brown,
                                ),
                                title = {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 5.dp)
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
                        // Determines which screen to display
                        navController = rememberNavController()
                        SetupNavGraph(navController = navController, viewModel = viewModel)
                    }
                }
            }
        }
    }
}