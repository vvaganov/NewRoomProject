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
    ): View? {
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.getString("key")
        val dateTime = LocalDateTime.parse(args, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val formaterData = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formaterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val data = dateTime.format(formaterData)
        val time = dateTime.format(formaterTime)
        Log.i("!!!", "InBundle = $dateTime")

        binding.btnSaveCons.setOnClickListener {
            viewModel.insertCalorieConsumption(
                binding.inputConsumpCallorie.text.toString().toInt(),
                data,
                time,
            )
        }
        binding.btnBackToDashboard.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        viewModel.initUiState(binding = binding, data = data)


    }
}

