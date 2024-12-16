package com.example.newroomproject

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.Date

class Converters {
    // из двух точек времени получить полных лет
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFullYears(dateStartPeriod: Long):Int{
        val date = Instant.ofEpochMilli(dateStartPeriod).atZone(ZoneId.systemDefault()).toLocalDate()
        val currentDate = LocalDate.now()
        val period = Period.between(date, currentDate)
        val fullYears = period.years
        return fullYears
    }

// Из точки времени в строку определенного формата
    @SuppressLint("SimpleDateFormat")
    fun timestampToString(timestamp: Long, format: String): String {
        val formatter = SimpleDateFormat(format)
        val date = Date(timestamp)
        return formatter.format(date)
    }
    // Из строки определенного формата в точку времени
    @SuppressLint("SimpleDateFormat")
    fun stringToTimestamp(dateString: String, format: String): Long {
        val formatter = SimpleDateFormat(format)
        val date = formatter.parse(dateString)
        if (date != null) {
            return date.time
        }
        return System.currentTimeMillis()
    }
}