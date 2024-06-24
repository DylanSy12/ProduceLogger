package com.example.producelogger.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.producelogger.Constants
import com.example.producelogger.ui.theme.Brown
import com.example.producelogger.ui.theme.DarkRed
import com.example.producelogger.ui.theme.MediumGreen

/**
 * A [Composable] function for creating a popup asking for the user to input a password
 *
 * @param onDismissRequest What happens when the user closes the popup
 * @param onCorrectPassword What happens when the user inputs the correct password
 * @param onIncorrectPassword What happens when the user inputs the incorrect password
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordPopup(
    onDismissRequest: () -> Unit,
    onCorrectPassword: () -> Unit,
    onIncorrectPassword: () -> Unit
) {
    var inputtedPassword by remember { mutableStateOf("") }
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(5.dp, Brown)
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
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Brown,
                    ),
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                Text(
                    text = "Password:",
                    style = TextStyle(
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        color = Brown
                    )
                )
                // TextField for inputting the password
                TextField(
                    value = inputtedPassword,
                    singleLine = true,
                    onValueChange = { inputtedPassword = it },
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        color = Brown
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                // Cancel/Confirm Buttons
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
                                textAlign = TextAlign.Center,
                                color = DarkRed
                            )
                        )
                    }
                    TextButton(
                        onClick = {
                            // Checks for if the inputted password is correct
                            if (inputtedPassword == Constants.PASSWORD) onCorrectPassword()
                            else onIncorrectPassword()
                        },
                        modifier = Modifier.padding(top = 8.dp, start = 12.dp, bottom = 10.dp),
                    ) {
                        Text(
                            text = "Confirm",
                            style = TextStyle(
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                color = MediumGreen
                            )
                        )
                    }
                }
            }
        }
    }
}

/**
 * A [Composable] function for creating a popup with a message
 *
 * @param onConfirmRequest What happens when the user presses the confirm button
 * @param onDismissRequest What happens when the user closes the popup
 * @param text What the popup displays
 */
@Composable
fun AlertPopup(onConfirmRequest: () -> Unit, onDismissRequest: () -> Unit, text: String) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(5.dp, Brown)
        ) {
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header for the popup
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Brown,
                    ),
                    modifier = Modifier.padding(
                        top = 10.dp,
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    )
                )
                // Cancel/Confirm Buttons
                Row {
                    if (onConfirmRequest != onDismissRequest) {
                        TextButton(
                            onClick = { onDismissRequest() },
                            modifier = Modifier.padding(
                                bottom = 10.dp,
                                end = 20.dp,
                                top = 0.dp,
                                start = 0.dp
                            ),
                        ) {
                            Text(
                                text = "Cancel",
                                style = TextStyle(
                                    fontSize = 30.sp,
                                    textAlign = TextAlign.Center,
                                    color = DarkRed
                                )
                            )
                        }
                    }
                    TextButton(
                        onClick = { onConfirmRequest() },
                        modifier = Modifier.padding(bottom = 10.dp),
                    ) {
                        Text(
                            text = "Confirm",
                            style = TextStyle(
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                color = MediumGreen
                            )
                        )
                    }
                }
            }
        }
    }
}