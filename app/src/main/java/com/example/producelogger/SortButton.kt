package com.example.producelogger

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.producelogger.ui.theme.Brown
import com.example.producelogger.ui.theme.DarkGreen

@Composable
fun SortButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    border: BorderStroke = BorderStroke(1.5F.dp, Brown),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = DarkGreen,
        contentColor = Brown
    )
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        border = border,
        colors = colors
    ) {

    }
    Text(text = if (sortOrder) "↑" else "↓")
}