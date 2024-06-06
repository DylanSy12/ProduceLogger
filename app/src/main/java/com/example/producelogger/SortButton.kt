package com.example.producelogger

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.producelogger.ui.theme.Brown
import com.example.producelogger.ui.theme.DarkGreen

/**
 * [Composable]
 */
@Composable
fun SortButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(
        containerColor = DarkGreen,
        contentColor = Brown
    ),
    sortingBy: String
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {
        if (sortBy == sortingBy) Icon(if (sortOrder) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, "", modifier = Modifier.fillMaxSize())
    }
}