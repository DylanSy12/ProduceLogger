package com.example.producelogger

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            HarvestedProduce(navController = navController, produceList = harvestList)
        }
    }
}
@Composable
fun HarvestedProduce(navController: NavController, produceList: ArrayList<Map<String, String>>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(0.dp, 60.dp)
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
        Divider(color = Color.Green)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                "Date", modifier = Modifier
                    .size(180.dp, 40.dp)
                    .padding(10.dp, 0.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            )
            Divider(
                color = Color.Green,
                modifier = Modifier.size(1.dp, 40.dp)
            )
            Text(
                "Item", modifier = Modifier
                    .size(360.dp, 40.dp)
                    .padding(10.dp, 0.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            )
            Divider(
                color = Color.Green,
                modifier = Modifier.size(1.dp, 40.dp)
            )
            Text(
                "Weight", modifier = Modifier
                    .size(180.dp, 40.dp)
                    .padding(10.dp, 0.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
        Divider(
            color = Color.Green,
            modifier = Modifier.height(3.dp)
        )
        var rowColor: Boolean = true
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
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (rowColor) Color.LightGray else Color.White
            )
    ) {
        Text(
            AnnotatedString(dateHarvested),
            modifier = Modifier
                .size(180.dp, 40.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        )
        Divider(
            color = Color.Green,
            modifier = Modifier.size(1.dp, 40.dp)
        )
        Text(
            AnnotatedString(produce),
            modifier = Modifier
                .size(360.dp, 40.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        )
        Divider(
            color = Color.Green,
            modifier = Modifier.size(1.dp, 40.dp)
        )
        Text(
            AnnotatedString(weight),
            modifier = Modifier
                .size(180.dp, 40.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 25.sp,
                textAlign = TextAlign.Center
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