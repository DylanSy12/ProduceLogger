package com.example.producelogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.producelogger.ui.theme.ProduceLoggerTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController

@Composable
fun HarvestLogComposable(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // A surface container using the 'background' color from the theme
        HarvestedProduce(produceList = harvestList)
    }
}
@Composable
fun HarvestedProduce(produceList: ArrayList<Map<String, String>>) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(0.dp, 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            "Date Harvested", modifier = Modifier
                .size(180.dp, 40.dp)
                .padding(10.dp, 0.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        )

        Text(
            "Item", modifier = Modifier
                .size(360.dp, 40.dp)
                .padding(10.dp, 0.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        )
        Text(
            "Weight (lbs.)", modifier = Modifier
                .size(180.dp, 40.dp)
                .padding(10.dp, 0.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        )
    }

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(produceList) { harvest ->
            if (harvest["date"] != null && harvest["item"] != null && harvest["weight"] != null) {
                AddItem(
                    dateHarvested = harvest["date"]!!,
                    produce = harvest["item"]!!,
                    weight = harvest["weight"]!!
                )
            }
        }
    }
}

@Composable
fun AddItem(dateHarvested: String, produce: String, weight: String) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            AnnotatedString(dateHarvested),
            modifier = Modifier
                .size(180.dp, 40.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        Text(
            AnnotatedString(produce),
            modifier = Modifier
                .size(360.dp, 40.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        Text(
            AnnotatedString(weight),
            modifier = Modifier
                .size(180.dp, 40.dp)
                .padding(10.dp, 5.dp),
            style = TextStyle(
                fontSize = 10.sp
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