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


/**
 * Formats a [String] representing a [Date] into mm/dd/yyyy format
 *
 * @param date The [String] to be formatted
 * @return [date] reformatted into mm/dd/yyyy format
 */
fun reformatDate(date: String): String =
    if (date[2] != '/') {
        val dateList = date.split("-")
        dateList[1] + "/" + dateList[2].take(2) + "/" + dateList[0]
    }
    else date