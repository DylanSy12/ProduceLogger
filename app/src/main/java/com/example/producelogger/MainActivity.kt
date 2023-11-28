package com.example.producelogger

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.producelogger.ui.theme.ProduceLoggerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProduceLoggerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HarvestedProduce(harvestList)
                }
            }
        }
    }
}

@Composable
fun HarvestedProduce(produceList: List<String>) {


    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(0.dp, 20.dp)
    ) {
        Text(
            "Date Harvested", modifier = Modifier
                .width(75.dp)
                .padding(start = 10.dp)
        )

        Text(
            "Item", modifier = Modifier
                .width(175.dp)
                .padding(10.dp, 0.dp)
        )
        Text(
            "Weight (lbs.)", modifier = Modifier
                .width(85.dp)
                .padding(10.dp, 0.dp)
        )
    }

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(produceList) { item ->
            AddItem(dateHarvested = item, produce = "", weight = "")
        }
    }
}

@Composable
fun AddItem(dateHarvested: String, produce: String, weight: String) {
    val context = LocalContext.current
    Row(

        horizontalArrangement = Arrangement.Center
    ) {

        ClickableText(
            AnnotatedString(dateHarvested), onClick = {},
            modifier = Modifier
                .width(75.dp)
                .padding(10.dp, 5.dp),
        )
        ClickableText(
            AnnotatedString(produce), onClick = {},
            modifier = Modifier
                .width(175.dp)
                .padding(10.dp, 5.dp),
        )
        ClickableText(
            AnnotatedString(weight), onClick = {},
            modifier = Modifier
                .width(85.dp)
                .padding(10.dp, 5.dp),
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProduceLoggerTheme {
        Greeting("Android")
    }
}