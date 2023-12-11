package com.example.producelogger

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Row {
                            Text("Produce Logger")
                        }
                    }
                )
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(0.dp, 60.dp)
            ) {
                Text(
                    text = "Date",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                )
                var temp = getCurrentDateTime().toString("MM/dd/yyyy")
                var date by remember { mutableStateOf(TextFieldValue(temp)) }
                harvest["date"] = temp
                TextField(
                    value = date,
                    onValueChange = {
                        date = it
                        harvest["date"] = date.text
                    },
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = "Item",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                )
                var item by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = item,
                    onValueChange = {
                        item = it
                        harvest["item"] = item.text
                    },
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = "Weight (lbs.)",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                )
                var weight by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = weight,
                    onValueChange = {
                        weight = it
                        harvest["weight"] = weight.text+" lbs."
                    },
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Button(
                    modifier = Modifier.padding(0.dp, 10.dp),
                    onClick = {
                        harvestList.add(harvest)
                        navController.navigate(Screen.HarvestLog.route) {
                            popUpTo(Screen.HarvestLog.route) {
                                saveState = true
                                inclusive = true
                            }
                        }
                    }
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun screenPreviewLogger() {
    ProduceLoggerTheme {
        HarvestRecorderComposable(navController = rememberNavController())
    }
}