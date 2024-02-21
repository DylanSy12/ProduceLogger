package com.example.producelogger

fun List<Harvest>.sort(): List<Harvest> {
    return if (sortBy == "D") this.sortByDate(sortOrder) else if (sortBy == "I") this.sortByItem(sortOrder) else this.sortByWeight(sortOrder)
}

fun List<Harvest>.sortByDate(descending: Boolean): List<Harvest> {
    return if (descending) this.sortedByDescending { it.date } else this.sortedBy { it.date }
}

fun List<Harvest>.sortByItem(descending: Boolean): List<Harvest> {
    return if (descending) this.sortedByDescending { it.item } else this.sortedBy { it.item }
}

fun List<Harvest>.sortByWeight(descending: Boolean): List<Harvest> {
    return if (descending) this.sortedByDescending { it.weight } else this.sortedBy { it.weight }
}