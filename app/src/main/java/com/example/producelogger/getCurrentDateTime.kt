package com.example.producelogger

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Formats the [Date] into a [String]
 *
 * @param format How to format the [Date]
 * @param locale The [Locale]
 * @return The [Date] as a [String] in the format of [format] and locale of [locale]
  */
fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String =
    SimpleDateFormat(format, locale).format(this)

/**
 * Gets the current [Date]
 *
 * @return The current [Date]
 */
fun getCurrentDateTime(): Date = Calendar.getInstance().time