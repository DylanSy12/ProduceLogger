package com.example.producelogger

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Formats the [Date] into a [String].
 *
 * @param format How to format the [String].
 * @param locale The [Locale].
 * @return The [Date] as a [String] in the format of [format] and locale of [locale].
  */
fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

/**
 * Gets the current [Date].
 *
 * @return The current [Date].
 */
fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}