package com.example.newroomproject.ui.dashBoard

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.newroomproject.databinding.FragmentDashBoardBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.newroomproject.R
import com.example.newroomproject.ui.calorieSpendList.CalorieSpendListFragment
import com.example.newroomproject.utils.DatePickerFragment
import java.time.LocalDateTime
import java.time.ZoneId

@AndroidEntryPoint
class DashBoardFragment : Fragment() {

    private val binding: FragmentDashBoardBinding by lazy {
        FragmentDashBoardBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: DashBoardViewModel by viewModels()
    private var selectedDateTime: LocalDateTime = LocalDateTime.now(ZoneId.systemDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUiState(savedInstanceState)

        viewModel.dashUiState.observe(viewLifecycleOwner) { state ->
            binding.tvCalendar.text = state.data
            binding.tvNumberCalorySpend.text = state.metabolism.toString()
        }

        binding.tvCalendar.setOnClickListener {
            DatePickerFragment() { dataTime ->
                selectedDateTime = dataTime
                viewModel.changeToData(selectedDateTime)
            }
                .show(childFragmentManager, "datePicker")
        }

        binding.btnParishEnergy.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("key", selectedDateTime)
            parentFragmentManager.commit {
                replace<CalorieSpendListFragment>(R.id.fragment_container_view, null, bundle)
                addToBackStack(null)
            }
        }
    }

    private fun initUiState(savedInstanceState: Bundle?) {
        viewModel.initUiState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Key", binding.tvCalendar.text.toString())
    }
}