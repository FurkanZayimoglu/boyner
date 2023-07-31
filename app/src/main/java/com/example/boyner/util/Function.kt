package com.example.boyner.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTime(dateTimeString: String): String? {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val date = inputFormat.parse(dateTimeString)
        val outputFormat = SimpleDateFormat("HH:mm:ss", Locale.US)
        date?.let { outputFormat.format(it) }
    } catch (e: Exception) {
        " saat bilgisi alınamadı."
    }
}





