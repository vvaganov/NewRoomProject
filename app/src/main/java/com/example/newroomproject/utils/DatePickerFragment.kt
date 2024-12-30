package com.example.newroomproject.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class DatePickerFragment(private val listener: (LocalDateTime) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

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
