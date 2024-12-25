package com.example.newroomproject.ui.calorieSpendList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.newroomproject.databinding.FragmentCalorieConsumptionListBinding
import com.example.newroomproject.R
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class CalorieSpendListFragment : Fragment() {
    private val binding: FragmentCalorieConsumptionListBinding by lazy {
        FragmentCalorieConsumptionListBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: CalorieConsumptionListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataTime = DateTimeParse(arguments?.getString("key")).dataTime()

        val adapter = CalorieSpendListAdapter(emptyList())
        binding.rvSpendList.adapter = adapter

        viewModel.consumptionUiState.observe(viewLifecycleOwner) { state ->
            adapter.dataSet = state.listConsumptions
            adapter.notifyDataSetChanged()
            binding.tvConsListTitle.text = state.data
        }

        binding.btnSaveCons.setOnClickListener {
            viewModel.insertCalorieConsumption(
                binding.inputConsumpCallorie.text.toString().toInt(),
                dataTime.data,
                dataTime.time,
            )
            binding.inputConsumpCallorie.apply {
                text?.clear()
                hint = getString(R.string.input_spend_calorie)
                clearFocus()
            }
        }

        binding.btnBackToDashboard.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        viewModel.initUiState(data = dataTime.data)
    }
}

class DateTimeParse(private val dataTime: String?) {

    fun dataTime(): TimeData {
        val dateTime = LocalDateTime.parse(dataTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val data = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        val time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        return TimeData(data, time)
    }
}

data class TimeData(val data: String, val time: String)




