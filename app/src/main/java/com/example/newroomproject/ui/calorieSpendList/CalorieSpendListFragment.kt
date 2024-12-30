package com.example.newroomproject.ui.calorieSpendList

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.newroomproject.databinding.FragmentCalorieConsumptionListBinding
import com.example.newroomproject.R
import com.example.newroomproject.ui.dashBoard.DashBoardFragment
import com.example.newroomproject.utils.DatePickerFragment
import com.example.newroomproject.utils.DateTimeParse
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

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

        var selectedDataTime = DateTimeParse(arguments?.getSerializable("key") as LocalDateTime).dataTime()

        val adapter = CalorieSpendListAdapter(emptyList())
        binding.rvSpendList.adapter = adapter

        viewModel.consumptionUiState.observe(viewLifecycleOwner) { state ->
            adapter.dataSet = state.listConsumptions
            adapter.notifyDataSetChanged()
            binding.tvCalendarSpend.text = state.data
        }

        binding.tvCalendarSpend.setOnClickListener {
            DatePickerFragment() { dataTime ->
                selectedDataTime = DateTimeParse(dataTime).dataTime()
                viewModel.changeToData(dataTime)
            }
                .show(childFragmentManager, "datePicker")
        }

        binding.btnSaveCons.setOnClickListener {
            viewModel.insertCalorieConsumption(
                binding.inputConsumpCallorie.text.toString().toInt(),
                selectedDataTime.data,
                selectedDataTime.time,
            )

            binding.inputConsumpCallorie.apply {
                text?.clear()
                hint = getString(R.string.input_spend_calorie)
                clearFocus()
            }
        }

        binding.btnBackToDashboard.setOnClickListener {
            parentFragmentManager.commit {
                replace<DashBoardFragment>(R.id.fragment_container_view,)
            }
        }

        viewModel.initUiState(data = selectedDataTime.data)
    }
}






