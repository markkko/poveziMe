package com.example.markkko.povezime.app.util

fun clean(number: Float?): Float {
    return number ?: 0f
}

fun clean(number: Int?): Int {
    return number ?: 0
}

fun clean(number: Long?): Long {
    return number ?: 0L
}

fun clean(number: Double?): Double {
    return number ?: 0.0
}
