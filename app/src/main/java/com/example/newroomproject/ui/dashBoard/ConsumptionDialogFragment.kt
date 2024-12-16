package com.example.newroomproject.ui.dashBoard

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.newroomproject.R



class ConsumptionDialogFragment(
    private val callBack: (Int) -> Unit
) : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_with_input, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextInput)

        return AlertDialog
            .Builder(requireContext())
            .setTitle("Добавьте расход каллорий, ккал")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { _, _ ->
                callBack(editText.text.toString().toInt())
                dismiss()

            }
            .setNegativeButton("Отменить") { _, _ ->
                dismiss()
            }
            .create()
    }
}