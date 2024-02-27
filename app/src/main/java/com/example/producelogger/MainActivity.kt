package com.example.producelogger

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
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
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.producelogger.ui.theme.Brown
import com.example.producelogger.ui.theme.DarkGreen
import com.example.producelogger.ui.theme.ProduceLoggerTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        File("/storage/emulated/0/${Environment.DIRECTORY_DOWNLOADS}").mkdirs()
        // Creates a test harvestList
        if (Constants.USE_TEST_DATA) {
            var c = 1.0
            var i = true
            while (c <= 11) {
                harvestList.add(Harvest(date = "1/${c.toInt()}/2024", item = if (i) "apple" else "banana", weight = "${c*1.25}"))
                c += 0.5
                i = !i
            }
        }

        // Sets screen content
        setContent {
            ProduceLoggerTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
                                        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
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
                        SetupNavGraph(navController = navController)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // check if the tablet is API 30 (Android 11) or above
        // 'all files access' is only in Android 11+
        if (Build.VERSION.SDK_INT >= 30 && !Environment.isExternalStorageManager()) {
            // create intent to open settings
            val intent = Intent().apply {
                action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
            }
            // open settings
            startActivity(intent)
        }
        // if the tablet is a lower version, we use the old permissions
        if (Build.VERSION.SDK_INT < 30) {
            // check read and write permissions
            if (ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) or ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                try {
                    // request permissions if not granted
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ), 100
                    )
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
    }
}