package com.example.producelogger

import com.example.producelogger.data.Harvest

/**
 * Sorts the [List] of [Harvests][Harvest] by different values of the [Harvests][Harvest] depending
 * on the value of [sortBy] in ascending/descending order based on the value of [sortOrder]
 *
 * @return The sorted [List] of [Harvests][Harvest]
 */
fun List<Harvest>.sort(): List<Harvest> =
    when (sortBy) {
        "D" -> this.sortByDate(sortOrder)
        "I" -> this.sortByItem(sortOrder)
        "W" -> this.sortByWeight(sortOrder)
        else -> this
    }

/**
 * Sorts the [List] of [Harvests][Harvest] chronologically by [date][Harvest.date] in ascending/descending
 * order based on the value of [descending]
 *
 * @param descending Whether or not the [List] of [Harvests][Harvest] should be sorted in descending order
 * @return The [List] of [Harvests][Harvest] sorted by [date][Harvest.date]
 */
fun List<Harvest>.sortByDate(descending: Boolean): List<Harvest> =
    if (descending) this.sortedByDescending { it.date } else this.sortedBy { it.date }

/**
 * Sorts the [List] of [Harvests][Harvest] alphabetically by [item][Harvest.item] in ascending/descending
 * order based on the value of [descending]
 *
 * @param descending Whether or not the [List] of [Harvests][Harvest] should be sorted in descending order
 * @return The [List] of [Harvests][Harvest] sorted by [item][Harvest.item]
 */
fun List<Harvest>.sortByItem(descending: Boolean): List<Harvest> =
    if (descending) this.sortedByDescending { it.item } else this.sortedBy { it.item }

/**
 * Sorts the [List] of [Harvests][Harvest] numerically by [weight][Harvest.weight] in ascending/descending
 * order based on the value of [descending]
 *
 * @param descending Whether or not the [List] of [Harvests][Harvest] should be sorted in descending order
 * @return The [List] of [Harvests][Harvest] sorted by [weight][Harvest.weight]
 */
fun List<Harvest>.sortByWeight(descending: Boolean): List<Harvest> =
    if (descending) this.sortedByDescending { it.weight } else this.sortedBy { it.weight }