package com.example.markkko.povezime.core.util

import org.joda.time.DateTime
import org.joda.time.Hours
import org.joda.time.Minutes
import org.joda.time.format.DateTimeFormat
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*


fun covertTimestampToJodaDateTime(timestamp: Timestamp?): DateTime {
    if (timestamp == null) {
        return DateTime()
    }

    val formatter = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss")
    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return formatter.parseDateTime(df.format(timestamp))
}

fun convertJodaDateTimeToString(dateTime: DateTime): String {
    val fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
    return fmt.print(dateTime)
}

fun parseJodaDateTime(dateTimeString: String): DateTime {
    val formatter = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss")
    return formatter.parseDateTime(dateTimeString)
}

fun convertJodaDateTimeToStringDateOnly(dateTime: DateTime): String {
    val fmt = DateTimeFormat.forPattern("yyyy-MM-dd")
    return fmt.print(dateTime)
}

fun convertJavaDateToString(date: Date): String {
    val df = SimpleDateFormat("yy-MM-dd")
    return df.format(date)
}

fun convertTimeToString(hours: Int, minutes: Int) : String{
    val newHours = if (hours < 10) "0$hours" else hours.toString()
    val newMinutes = if (minutes < 10) "0$minutes" else minutes.toString()
    return "$newHours:$newMinutes:00"
}

