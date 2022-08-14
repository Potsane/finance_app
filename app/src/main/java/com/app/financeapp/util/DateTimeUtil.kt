package com.app.financeapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
private fun dateStringToTimeMillis(date: String): Long {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return format.parse(date)?.time ?: 0
}

@SuppressLint("SimpleDateFormat")
fun getFormattedDate(date: String): String {
    val millis = dateStringToTimeMillis(date)
    val dateFormat = SimpleDateFormat("dd MMM, yyyy")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(Date(millis))
}