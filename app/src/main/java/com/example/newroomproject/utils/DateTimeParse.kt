package com.example.newroomproject.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeParse(private val dataTime: LocalDateTime) {

    fun dataTime(): TimeData {
        val data = dataTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        val time = dataTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        return TimeData(data, time)
    }
}

data class TimeData(val data: String, val time: String)