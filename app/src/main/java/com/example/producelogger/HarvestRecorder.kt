package com.example.producelogger

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorLong
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.producelogger.ui.theme.ProduceLoggerTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HarvestRecorderComposable(navController: NavController) {
    var harvest: MutableMap<String, String> = mutableMapOf()
    var openPasswordPopup = remember { mutableStateOf(false) }
    var openIncorrectPasswordPopup = remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = com.example.producelogger.ui.theme.DarkGreen,
                        titleContentColor = com.example.producelogger.ui.theme.Brown,
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Date",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 45.sp,
                        textAlign = TextAlign.Center
                    )
                )
                var temp = getCurrentDateTime().toString("MM/dd/yyyy")
                var date by remember { mutableStateOf(TextFieldValue(temp)) }
                harvest["date"] = temp
                TextField(
                    value = date,
                    singleLine = true,
                    onValueChange = {
                        date = it
                        harvest["date"] = date.text
                    },
                    textStyle = TextStyle(
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .width(600.dp)
                )
                Text(
                    text = "Item",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 45.sp,
                        textAlign = TextAlign.Center
                    )
                )
                var item by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = item,
                    singleLine = true,
                    onValueChange = {
                        item = it
                        harvest["item"] = item.text
                    },
                    textStyle = TextStyle(
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .width(600.dp)
                )
                Text(
                    text = "Weight",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 45.sp,
                        textAlign = TextAlign.Center
                    )
                )
                var weight by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = weight,
                    singleLine = true,
                    onValueChange = {
                        weight = it
                        harvest["weight"] = weight.text+" lbs."
                    },
                    textStyle = TextStyle(
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .width(600.dp),
                    trailingIcon = {
                        Text(
                            text = " lbs.",
                            style = TextStyle(
                                fontSize = 35.sp
                            )
                        )
                    }
                )
                Button(
                    modifier = Modifier.padding(0.dp, 10.dp),
                    onClick = {
                        openPasswordPopup.value = true
                    }
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
            when {
                openPasswordPopup.value -> {
                    PasswordPopup(
                        onDismissRequest = { openPasswordPopup.value = false },
                        onCorrectPassword = {
                            openPasswordPopup.value = false
                            harvestList.add(harvest)
                            navController.navigate(Screen.HarvestLog.route) {
                                popUpTo(Screen.HarvestLog.route) {
                                    saveState = true
                                    inclusive = true
                                }
                            }
                        },
                        onIncorrectPassword = {
                            openIncorrectPasswordPopup.value = true
                            openPasswordPopup.value = false
                        }
                    )
                }
            }
            when {
                openIncorrectPasswordPopup.value -> {
                    IncorrectPasswordPopup(
                        onDismissRequest = {
                            openIncorrectPasswordPopup.value = false
                            openPasswordPopup.value = true
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordPopup(
    onDismissRequest: () -> Unit,
    onCorrectPassword: () -> Unit,
    onIncorrectPassword: () -> Unit
) {
    var inputtedPassword by remember { mutableStateOf(TextFieldValue("")) }
    Dialog( onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(5.dp, com.example.producelogger.ui.theme.DarkGreen)
        ) {
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enter password to finish recording the harvest",
                    style = TextStyle(
                        fontSize = 35.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                Text(
                    text = "Password:",
                    style = TextStyle(
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center
                    )
                )
                TextField(
                    value = inputtedPassword,
                    singleLine = true,
                    onValueChange = {
                        inputtedPassword = it
                    },
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(top = 8.dp, end = 12.dp, bottom = 10.dp),
                    ) {
                        Text(
                            text = "Cancel",
                            style = TextStyle(
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                    TextButton(
                        onClick = {
                            if (inputtedPassword.text == password) {
                                onCorrectPassword()
                            } else {
                                onIncorrectPassword()
                            }
                        },
                        modifier = Modifier.padding(top = 8.dp, start = 12.dp, bottom = 10.dp),
                    ) {
                        Text(
                            text = "Confirm",
                            style = TextStyle(
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncorrectPasswordPopup(onDismissRequest: () -> Unit) {
    AlertDialog(
        title = { Text(text = "Incorrect Password") },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(
                    text = "Confirm",
                    style = TextStyle(
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun screenPreviewLogger() {
    ProduceLoggerTheme {
        HarvestRecorderComposable(navController = rememberNavController())
    }
}