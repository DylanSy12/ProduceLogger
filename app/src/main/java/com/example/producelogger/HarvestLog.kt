package com.example.producelogger

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorLong
import com.example.producelogger.ui.theme.ProduceLoggerTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HarvestLogComposable(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
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
            HarvestedProduce(navController = navController, produceList = harvestList)
        }
    }
}
@Composable
fun HarvestedProduce(navController: NavController, produceList: ArrayList<Map<String, String>>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, bottom = 5.dp)
    ) {
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
            border = BorderStroke(5.dp, com.example.producelogger.ui.theme.Brown),
            colors = ButtonDefaults.buttonColors(
                containerColor = com.example.producelogger.ui.theme.DarkGreen,
                contentColor = com.example.producelogger.ui.theme.Brown
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
        Divider(color = com.example.producelogger.ui.theme.Brown)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                "Date", modifier = Modifier
                    .weight(1F)
                    .padding(10.dp, 0.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    color = com.example.producelogger.ui.theme.Brown
                )
            )
            Divider(
                color = com.example.producelogger.ui.theme.Brown,
                modifier = Modifier.size(1.dp, 40.dp)
            )
            Text(
                "Item", modifier = Modifier
                    .weight(1.75F)
                    .padding(10.dp, 0.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    color = com.example.producelogger.ui.theme.Brown
                )
            )
            Divider(
                color = com.example.producelogger.ui.theme.Brown,
                modifier = Modifier.size(1.dp, 40.dp)
            )
            Text(
                "Weight", modifier = Modifier
                    .weight(1F)
                    .padding(10.dp, 0.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    color = com.example.producelogger.ui.theme.Brown
                )
            )
        }
        Divider(
            color = com.example.producelogger.ui.theme.Brown,
            modifier = Modifier.height(3.dp)
        )
        var rowColor = true
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(produceList) { harvest ->
                if (harvest["date"] != null && harvest["item"] != null && harvest["weight"] != null) {
                    AddItem(
                        dateHarvested = harvest["date"]!!,
                        produce = harvest["item"]!!,
                        weight = harvest["weight"]!!,
                        rowColor = rowColor
                    )
                    rowColor = !rowColor
                }
            }
        }
    }
}

@Composable
fun AddItem(dateHarvested: String, produce: String, weight: String, rowColor: Boolean) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp, max = 80.dp)
            .background(
                color = if (rowColor) Color.LightGray else Color.White
            )
    ) {
        Text(
            AnnotatedString(dateHarvested),
            modifier = Modifier
                .weight(1F)
                .heightIn(min = 40.dp, max = 80.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Left,
                color = com.example.producelogger.ui.theme.Brown
            )
        )
        Divider(
            color = com.example.producelogger.ui.theme.Brown,
            modifier = Modifier.width(1.dp).fillMaxHeight()
        )
        Text(
            AnnotatedString(produce),
            modifier = Modifier
                .weight(1.75F)
                .heightIn(min = 40.dp, max = 80.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = com.example.producelogger.ui.theme.Brown
            )
        )
        Divider(
            color = com.example.producelogger.ui.theme.Brown,
            modifier = Modifier.width(1.dp).fillMaxHeight()
        )
        Text(
            AnnotatedString(weight),
            modifier = Modifier
                .weight(1F)
                .heightIn(min = 40.dp, max = 80.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Right,
                color = com.example.producelogger.ui.theme.Brown
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun screenPreviewLog() {
    ProduceLoggerTheme {
        HarvestLogComposable(navController = rememberNavController())
    }
}