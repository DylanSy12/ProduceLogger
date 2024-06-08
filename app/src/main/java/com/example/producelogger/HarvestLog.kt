package com.example.producelogger

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.producelogger.ui.theme.*

/**
 * A [Composable] function for displaying the screen for displaying recorded [Harvests][Harvest]
 *
 * @param navController The [NavController] controlling which screen is displayed
 * @param viewModel The [ViewModel] for managing [Harvest] data
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HarvestLogComposable(navController: NavController, viewModel: HarvestViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, bottom = 5.dp)
    ) {
        // Button to navigate to the HarvestRecorder screen
        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                switchScreens(navController, Screen.HarvestRecorder.route)
            },
            border = BorderStroke(5.dp, Brown),
            colors = ButtonDefaults.buttonColors(containerColor = DarkGreen, contentColor = Brown)
        ) {
            Text(
                text = "Record Harvest",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            )
        }

        Divider(color = Brown, modifier = Modifier.height(2.dp))

        // Filters
        // Header for the filters
        Text(
            text = "Filter for:",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                color = Brown
            )
        )
        // Labels for the filters
        Row(modifier = Modifier.fillMaxWidth()) {
            // Label for the Date filter
            Text(
                text = "Date",
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                )
            )
            // Label for the Item filter
            Text(
                text = "Item",
                modifier = Modifier.weight(1.75f),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                )
            )
            // Label for the Weight filter
            Text(
                text = "Weight",
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                )
            )
        }
        // TextFields for inputting the filters.
        var searchDate by remember { mutableStateOf("") }
        var searchItem by remember { mutableStateOf("") }
        var searchWeight by remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(bottom = 10.dp)) {
            // TextField for filtering for a Date
            OutlinedTextField(
                value = searchDate,
                onValueChange = {
                    viewModel.fetchHarvests()
                    searchDate = it
                },
                textStyle = TextStyle(
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                ),
                modifier = Modifier.padding(start = 8.dp, end = 8.dp).weight(1f)
            )
            // TextField for filtering an Item
            OutlinedTextField(
                value = searchItem,
                onValueChange = {
                    viewModel.fetchHarvests()
                    searchItem = it
                },
                textStyle = TextStyle(
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                ),
                modifier = Modifier.padding(start = 8.dp, end = 8.dp).weight(1.75f)
            )
            // TextField for filtering for a Weight
            OutlinedTextField(
                value = searchWeight,
                onValueChange = {
                    viewModel.fetchHarvests()
                    searchWeight = it
                },
                textStyle = TextStyle(
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                ),
                modifier = Modifier.padding(start = 8.dp, end = 8.dp).weight(1f)
            )
        }

        Divider(color = Brown, modifier = Modifier.height(2.dp))

        // HarvestLog
        // Headers for the columns
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            // Header for the Date column
            Row(modifier = Modifier.weight(1F), horizontalArrangement = Arrangement.Center) {
                SortButton(
                    onClick = {
                        viewModel.fetchHarvests()
                        if (sortBy == "D") sortOrder = !sortOrder
                        else sortBy = "D"
                    },
                    modifier = Modifier.size(36.dp, 40.dp).padding(8.dp, 5.dp),
                    sortingBy = "D"
                )
                Text(
                    text = "Date",
                    modifier = Modifier.padding(end = 10.dp).height(40.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = Brown
                    )
                )
            }
            Divider(color = Brown, modifier = Modifier.size(1.dp, 40.dp))
            // Header for the Item column
            Row(modifier = Modifier.weight(1.75F), horizontalArrangement = Arrangement.Center) {
                SortButton(
                    onClick = {
                        viewModel.fetchHarvests()
                        if (sortBy == "I") sortOrder = !sortOrder
                        else sortBy = "I"
                    },
                    modifier = Modifier.size(36.dp, 40.dp).padding(8.dp, 5.dp),
                    sortingBy = "I"
                )
                Text(
                    text = "Item",
                    modifier = Modifier.padding(end = 10.dp).height(40.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = Brown
                    )
                )
            }
            Divider(color = Brown, modifier = Modifier.size(1.dp, 40.dp))
            // Header for the Weight column
            Row(modifier = Modifier.weight(1F), horizontalArrangement = Arrangement.Center) {
                SortButton(
                    onClick = {
                        viewModel.fetchHarvests()
                        if (sortBy == "W") sortOrder = !sortOrder
                        else sortBy = "W"
                    },
                    modifier = Modifier.size(36.dp, 40.dp).padding(8.dp, 5.dp),
                    sortingBy = "W"
                )
                Text(
                    text = "Weight",
                    modifier = Modifier.padding(end = 10.dp).height(40.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = Brown
                    )
                )
            }
        }

        Divider(color = Brown, modifier = Modifier.height(4.dp))

        // Displays all the Harvests in harvestList
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(harvestList.sort()) { harvest ->
                // Check if the Harvest matches the filters
                if (
                    searchDate in harvest.date &&
                    searchItem in harvest.item &&
                    searchWeight in harvest.weight
                ) {
                    // Displays the Harvest
                    DisplayHarvest(harvest = harvest)
                    Divider(color = Brown, modifier = Modifier.height(0.5F.dp))
                }
            }
        }
    }
}

/**
 * A [Composable] function for displaying a [Row] showing a [Harvest's][Harvest] data.
 *
 * @param harvest The [Harvest] to be displayed
 */
@Composable
fun DisplayHarvest(harvest: Harvest) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().heightIn(min = 40.dp, max = 80.dp)
    ) {
        // Date
        val dateList = harvest.date.split("-")
        val date = dateList[1]+"/"+dateList[2].take(2)+"/"+dateList[0]
        Text(
            text = date,
            modifier = Modifier.weight(1F).heightIn(min = 40.dp, max = 80.dp).padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Left,
                color = Brown
            )
        )
        Divider(
            color = Brown,
            modifier = Modifier.width(1.dp).fillMaxHeight()
        )
        // Item
        Text(
            text = harvest.item,
            modifier = Modifier.weight(1.75F).heightIn(min = 40.dp, max = 80.dp).padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = Brown
            )
        )
        Divider(
            color = Brown,
            modifier = Modifier.width(1.dp).fillMaxHeight()
        )
        // Weight
        Text(
            text = "${harvest.weight} lbs.",
            modifier = Modifier.weight(1F).heightIn(min = 40.dp, max = 80.dp).padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Right,
                color = Brown
            )
        )
    }
}