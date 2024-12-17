package com.example.newroomproject.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.System.currentTimeMillis
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Converters {

    private val formatData = "dd.MM.yyyy"
    private val formatTime = "HH:mm:ss"

    // из двух точек времени получить полных лет
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFullYears(dateStartPeriod: String):Int{
        val dateStartPeriodMills = stringToTimestamp(dateStartPeriod)
        val date = Instant.ofEpochMilli(dateStartPeriodMills).atZone(ZoneId.systemDefault()).toLocalDate()
        val currentDate = LocalDate.now()
        val period = Period.between(date, currentDate)
        val fullYears = period.years
        return fullYears
    }

// Из точки времени в строку определенного формата
    @SuppressLint("SimpleDateFormat")
    fun timestampToString(currentTimesMills: Long): String {
        val formatter = SimpleDateFormat(formatData, Locale.getDefault())
        val date = Date(currentTimesMills)
        return formatter.format(date)
    }
    // Из строки определенного формата в точку времени
    @SuppressLint("SimpleDateFormat")
    fun stringToTimestamp(dateString: String): Long {
        val formatter = SimpleDateFormat(formatData, Locale.getDefault())
        val date = formatter.parse(dateString)
        if (date != null) {
            return date.time
        }
        return System.currentTimeMillis()
    }

    // создает из точки времени объект с полями дата и время
    fun timestampToDateTimeObject(currentTimesMills: Long): DataTime {
        val currentDate =  getFormater(formatData).format(Date(currentTimesMills))
        val currentTime = getFormater(formatTime).format(Date(currentTimesMills))
        return DataTime(
            currentDate,
            currentTime
        )
    }

    private fun getFormater(format: String): SimpleDateFormat {
        val formater = SimpleDateFormat(format, Locale.getDefault())
        formater.timeZone = TimeZone.getTimeZone("GMT+5")
        return formater
    }
}

data class DataTime(
    val data: String,
    val time: String
)