package com.example.newroomproject.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.Calendar
import kotlin.text.format

class DatePickerFragment(private val listener: (LocalDateTime) -> Unit) : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val formaterData = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formaterTime = DateTimeFormatter.ofPattern("HH:mm")

        val nowData = LocalDateTime.now(ZoneId.systemDefault())

        val year = nowData.year
        val month = nowData.monthValue - 1
        val day = nowData.dayOfMonth

        val dataPicker = DatePickerDialog(
            requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val currentTime = LocalTime.now(ZoneId.systemDefault())
                val selectedDateTime = LocalDateTime.of(
                    selectedYear,
                    selectedMonth + 1,
                    selectedDay,
                    currentTime.hour,
                    currentTime.minute
                )
                listener(selectedDateTime)
            }, year, month, day
        )

        return dataPicker
    }
}
