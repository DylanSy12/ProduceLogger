package com.example.producelogger.composables.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.producelogger.ui.theme.*
import androidx.lifecycle.ViewModel
import com.example.producelogger.composables.AlertPopup
import com.example.producelogger.Constants
import com.example.producelogger.data.Database
import com.example.producelogger.data.Harvest
import com.example.producelogger.composables.PasswordPopup
import com.example.producelogger.data.HarvestViewModel
import com.example.producelogger.navigation.Screen
import com.example.producelogger.database
import com.example.producelogger.getCurrentDateTime
import com.example.producelogger.navigation.switchScreens
import com.example.producelogger.toString
import com.example.producelogger.harvestList

/**
 * A [Composable] function for displaying the screen for recording [Harvests][Harvest]
 *
 * @param navController The [NavController] controlling which screen is displayed
 * @param viewModel The [ViewModel] for managing [Harvest] data
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HarvestRecorderComposable(navController: NavController, viewModel: HarvestViewModel, harvestToEdit: Harvest = Harvest()) {
    val harvest = remember { mutableStateOf(harvestToEdit) }
    val openPasswordPopup = remember { mutableStateOf(false) }
    val openIncorrectPasswordPopup = remember { mutableStateOf(false) }
    val openInputPopup = remember { mutableStateOf(false) }
    val openSpecialCharacterPopup = remember { mutableStateOf(false) }
    val openBackPopup = remember { mutableStateOf(false) }
    Column {
        // Button for going back to the HarvestLog screen
        Button(
            modifier = Modifier.padding(10.dp, 70.dp, 0.dp, 0.dp),
            onClick = { openBackPopup.value = true },
            border = BorderStroke(4.dp, Brown),
            colors = ButtonDefaults.buttonColors(containerColor = DarkGreen, contentColor = Brown)
        ) {
            Text(
                text = "<- Back",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                )
            )
        }
        Column(
            modifier = Modifier.padding(0.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // TextFields for inputting data
            // TextField for inputting the Date
            Text(
                text = "Date",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                )
            )
            // Gets current Date and converts it to the correct format
            val temp = getCurrentDateTime().toString("MM/dd/yyyy")
            var date by remember { mutableStateOf(temp) }
            harvest.value.date = temp
            TextField(
                value = date,
                singleLine = true,
                onValueChange = { date = it },
                textStyle = TextStyle(
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                ),
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
            )
            // TextField for inputting the Item
            Text(
                text = "Item",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                )
            )
            var item by remember { mutableStateOf("") }
            TextField(
                value = item,
                singleLine = true,
                onValueChange = { if (it.length <= 34) item = it },
                textStyle = TextStyle(
                    fontSize = 38.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                ),
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
            )
            // TextField for inputting Weight
            Text(
                text = "Weight",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                )
            )
            var weight by remember { mutableStateOf("") }
            TextField(
                value = weight,
                singleLine = true,
                onValueChange = { if (it.length <= 6) weight = it },
                textStyle = TextStyle(
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    color = Brown
                ),
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                trailingIcon = {
                    Text(text = " lbs.", style = TextStyle(fontSize = 35.sp, color = Brown))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Button to record the Harvest
            Button(
                modifier = Modifier.padding(0.dp, 10.dp),
                onClick = {
                    // Checks for empty fields or special characters
                    if (date == "" || weight == "" || item == "") openInputPopup.value = true
                    else if (!(weight.matches("-?[0-9]+(\\.[0-9]+)?".toRegex()))) {
                        openSpecialCharacterPopup.value = true
                    }
                    else {
                        // Records the inputs
                        harvest.value.date = date
                        harvest.value.item = item
                        // Trims the weight to 5 characters, then removes any periods at the end
                        var tempWeight = weight
                        if (weight[tempWeight.length - 1] == '.') tempWeight = weight.take(5)
                        harvest.value.weight = tempWeight
                        /*
                        Opens the password popup if required, otherwise records the harvest
                        and switches to the HarvestLog screen
                        */
                        if (Constants.REQUIRE_PASSWORD) openPasswordPopup.value = true
                        else {
                            database = Database(navController.context)
                            database.addHarvest(harvest.value)
//                            addHarvest(viewModel, harvest.value)
                            switchScreens(navController, Screen.HarvestLog.route, viewModel)
                        }
                    }
                },
                border = BorderStroke(8.dp, Brown),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkGreen,
                    contentColor = Brown
                )
            ) {
                Text(
                    text = "Record Harvest",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }

    // Popups
    // Popup for inputting the password
    when {
        openPasswordPopup.value -> {
            PasswordPopup(
                onDismissRequest = { openPasswordPopup.value = false },
                onCorrectPassword = {
                    database = Database(navController.context)
                    database.addHarvest(harvest.value)
                    openPasswordPopup.value = false
//                    addHarvest(viewModel, harvest.value)
                    switchScreens(navController, Screen.HarvestLog.route, viewModel)
                },
                onIncorrectPassword = {
                    openIncorrectPasswordPopup.value = true
                    openPasswordPopup.value = false
                }
            )
        }
    }
    // Popup for if the user inputted the wrong password
    when {
        openIncorrectPasswordPopup.value -> {
            AlertPopup(
                onConfirmRequest = {
                    openIncorrectPasswordPopup.value = false
                    openPasswordPopup.value = true
                },
                onDismissRequest = {
                    openIncorrectPasswordPopup.value = false
                    openPasswordPopup.value = true
                },
                text = "Incorrect Password"
            )
        }
    }
    // Popup for if the user did not enter values for all fields
    when {
        openInputPopup.value -> {
            AlertPopup(
                onConfirmRequest = { openInputPopup.value = false },
                onDismissRequest = { openInputPopup.value = false },
                text = "Please enter all values"
            )
        }
    }
    // Popup for if the user entered special characters in the field for the weight
    when {
        openSpecialCharacterPopup.value -> {
            AlertPopup(
                onConfirmRequest = { openSpecialCharacterPopup.value = false },
                onDismissRequest = { openSpecialCharacterPopup.value = false },
                text = "Please enter only numbers for the weight"
            )
        }
    }
    // Popup for confirming that the user wants to go back to the HarvestLog screen without saving
    when {
        openBackPopup.value -> {
            AlertPopup(
                onConfirmRequest = {
                    openBackPopup.value = false
                    viewModel.fetchHarvests()
                    switchScreens(navController, Screen.HarvestLog.route, viewModel)
                },
                onDismissRequest = { openBackPopup.value = false },
                text = "Going back will clear inputs, are you sure?"
            )
        }
    }
}

/**
 * Adds the [Harvest] to the Google Sheet, then updates [harvestList].
 *
 * @param viewModel The [ViewModel] for managing [Harvest] data
 * @param harvest The [Harvest] to be added to the Google Sheet
 */
fun addHarvest(viewModel: HarvestViewModel, harvest: Harvest) {
    viewModel.addHarvest(harvest)
    viewModel.fetchHarvests()
}