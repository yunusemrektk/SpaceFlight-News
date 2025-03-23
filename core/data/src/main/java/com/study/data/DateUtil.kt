package com.study.data

import android.annotation.SuppressLint
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale


@SuppressLint("NewApi")
fun convertDateFormat(iso8601Date: String): String {
    // Parse the ISO 8601 date string
    val instant = Instant.parse(iso8601Date)
    val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    // Calculate the difference in days from today
    val today = LocalDateTime.now()
    val daysDifference = ChronoUnit.DAYS.between(dateTime, today)

    return when {
        daysDifference <= 0 -> {
            // If it's today or in the future, return "today"
            "today"
        }

        daysDifference < 7 -> {
            // If it's within the last 7 days, return "x days ago"
            "$daysDifference days ago"
        }

        else -> {
            // Otherwise, return in "dd:MM:yyyy" format
            val formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy", Locale.getDefault())
            dateTime.format(formatter)
        }
    }
}
