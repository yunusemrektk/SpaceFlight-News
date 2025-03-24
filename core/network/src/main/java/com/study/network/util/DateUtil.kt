package com.study.network.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun convertDateFormat(iso8601Date: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val date: Date = try {
        dateFormat.parse(iso8601Date) ?: Date()
    } catch (e: Exception) {
        Date()
    }
    val currentDate = Date()

    val diffInMs: Long = currentDate.time - date.time
    val diffInDays: Long = TimeUnit.MILLISECONDS.toDays(diffInMs)

    return when {
        diffInDays == 0L -> {
            "today"
        }

        diffInDays > 0 && diffInDays < 7 -> {
            "$diffInDays days ago"
        }
        else -> {
            val formatter = SimpleDateFormat("dd:MM:yyyy", Locale.getDefault())
            formatter.format(date)
        }
    }
}