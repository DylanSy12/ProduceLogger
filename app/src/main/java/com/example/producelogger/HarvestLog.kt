package com.example.producelogger

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.producelogger.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HarvestLogComposable(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, bottom = 5.dp)
    ) {
        // Button to navigate to HarvestRecorder
        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                navController.navigate(Screen.HarvestRecorder.route) {
                    popUpTo(Screen.HarvestRecorder.route) {
                        saveState = true
                        inclusive = true
                    }
                }
            },
            border = BorderStroke(5.dp, Brown),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkGreen,
                contentColor = Brown
            )
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
        // Filter header
        Text(
            text = "Filter for:",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                color = Brown
            )
        )
        // Filter labels
        Row(modifier = Modifier.fillMaxWidth()) {
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
        // Filter input TextFields
        var searchDate by remember { mutableStateOf("") }
        var searchItem by remember { mutableStateOf("") }
        var searchWeight by remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 10.dp)
        ) {
            OutlinedTextField(
                value = searchDate,
                onValueChange = { searchDate = it },
                textStyle = TextStyle(
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                ),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .weight(1f)
            )
            OutlinedTextField(
                value = searchItem,
                onValueChange = { searchItem = it },
                textStyle = TextStyle(
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                ),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .weight(1.75f)
            )
            OutlinedTextField(
                value = searchWeight,
                onValueChange = { searchWeight = it },
                textStyle = TextStyle(
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                ),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .weight(1f)
            )
        }

        Divider(color = Brown, modifier = Modifier.height(2.dp))

        // HarvestLog
        // List headers
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.weight(1F),
                horizontalArrangement = Arrangement.Center
            ) {
                SortButton(
                    onClick = {
                        if (sortBy == "D") sortOrder = !sortOrder
                        else sortBy = "D"
                    },
                    modifier = Modifier
                        .size(36.dp, 40.dp)
                        .padding(8.dp, 5.dp)
                )
                Text(
                    text = "Date",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .height(40.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = Brown
                    )
                )
            }
            Divider(color = Brown, modifier = Modifier.size(1.dp, 40.dp))
            Row(
                modifier = Modifier.weight(1.75F),
                horizontalArrangement = Arrangement.Center
            ) {
                SortButton(
                    onClick = {
                        if (sortBy == "I") sortOrder = !sortOrder
                        else sortBy = "I"
                    },
                    modifier = Modifier
                        .size(36.dp, 40.dp)
                        .padding(8.dp, 5.dp)
                )
                Text(
                    text = "Item",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .height(40.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = Brown
                    )
                )
            }
            Divider(color = Brown, modifier = Modifier.size(1.dp, 40.dp))
            Row(
                modifier = Modifier.weight(1F),
                horizontalArrangement = Arrangement.Center
            ) {
                SortButton(
                    onClick = {
                        if (sortBy == "W") sortOrder = !sortOrder
                        else sortBy = "W"
                    },
                    modifier = Modifier
                        .size(36.dp, 40.dp)
                        .padding(8.dp, 5.dp)
                )
                Text(
                    text = "Weight",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .height(40.dp),
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

        // Displays harvestList
//        var rowColor by remember { mutableStateOf(true) }
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(harvestList.sort()) { harvest ->
                // Check for filters
                if (
                    searchDate in harvest.date &&
                    searchItem in harvest.item &&
                    searchWeight in harvest.weight
                ) {
                    // Displays Harvest
                    DisplayHarvest(harvest = harvest/*, rowColor = rowColor*/)
//                    rowColor = !rowColor
                    Divider(color = Brown, modifier = Modifier.height(0.5F.dp))
                }
            }
        }
    }
}

// Displays Harvest
@Composable
fun DisplayHarvest(harvest: Harvest/*, rowColor: Boolean*/) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp, max = 80.dp)
//            .background(color = if (rowColor) Color.LightGray else Color.White)
    ) {
        Text(
            text = harvest.date,
            modifier = Modifier
                .weight(1F)
                .heightIn(min = 40.dp, max = 80.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Left,
                color = Brown
            )
        )
        Divider(
            color = Brown,
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
        )
        Text(
            text = harvest.item,
            modifier = Modifier
                .weight(1.75F)
                .heightIn(min = 40.dp, max = 80.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = Brown
            )
        )
        Divider(
            color = Brown,
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
        )
        Text(
            text = "${harvest.weight} lbs.",
            modifier = Modifier
                .weight(1F)
                .heightIn(min = 40.dp, max = 80.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Right,
                color = Brown
            )
        )
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun ScreenPreviewLog() {
    ProduceLoggerTheme {
        HarvestLogComposable(navController = rememberNavController())
    }
}