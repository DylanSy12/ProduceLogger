package com.example.producelogger

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// Formats the date into a String
fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

// Gets the current date
fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}