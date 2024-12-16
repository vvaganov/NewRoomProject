package com.example.newroomproject

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import kotlin.text.format

class DatePickerFragment(private val listener: OnDateSetListener) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    interface OnDateSetListener {
        fun onDateSet(date: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dataPicker = DatePickerDialog(requireContext(), this, year, month, day)

        return dataPicker
    }

    @SuppressLint("DefaultLocale")
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val formattedDate = String.format("%02d.%02d.%d", day, month + 1, year, )
        listener.onDateSet(formattedDate)

    }
}