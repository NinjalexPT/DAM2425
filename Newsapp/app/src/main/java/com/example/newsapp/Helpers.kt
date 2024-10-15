package com.example.newsapp

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.parseDate(): Date? {
    val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    return SimpleDateFormat(pattern, Locale.getDefault())
        .parse(this)
}

fun Date.toYYMMDD() : String {
    val pattern = "yyyy-MM-dd"
    return SimpleDateFormat(pattern, Locale.getDefault())
        .format(this)

}