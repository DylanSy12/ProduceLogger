package com.example.producelogger

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
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
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(
        containerColor = DarkGreen,
        contentColor = Brown
    )
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {
        Icon(if (sortOrder) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, "", modifier = Modifier.fillMaxSize())
    }
}