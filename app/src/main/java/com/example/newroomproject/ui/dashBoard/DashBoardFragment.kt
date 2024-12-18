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
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DashBoardFragment : Fragment() {

    private val binding: FragmentDashBoardBinding by lazy {
        FragmentDashBoardBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: DashBoardViewModel by viewModels()

    val formater = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    var selectedDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initUiState(savedInstanceState)

        binding.tvCalendar.setOnClickListener {
            DatePickerFragment() { dataTime ->
                selectedDateTime = dataTime
                binding.tvCalendar.text = selectedDateTime.format(formater)
                viewModel.changeToData(selectedDateTime.format(formater), binding)
            }
                .show(childFragmentManager, "datePicker")

        }


        binding.btnParishEnergy.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("key", selectedDateTime.format(formatter))
            parentFragmentManager.commit {
                replace<CalorieSpendListFragment>(R.id.fragment_container_view, null, bundle)
                addToBackStack(null)
                Log.i("!!!", "OutBundle - $bundle")
            }
        }
    }

    private fun initUiState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            binding.tvCalendar.text = savedInstanceState.getString("Key")
            viewModel.initUiState(binding.tvCalendar.text.toString(), binding)

        } else {
            binding.tvCalendar.text = selectedDateTime.format(formater)
            viewModel.initUiState(binding.tvCalendar.text.toString(), binding)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = binding.tvCalendar.text.toString()
        outState.putString("Key", bundle)
        Log.i("!!!", "onSaveInstanceState: ${binding.tvCalendar.text}")
    }

}