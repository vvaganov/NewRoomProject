package com.example.newroomproject.ui.calorieConsumptionList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newroomproject.databinding.FragmentCalorieConsumptionListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalorieConsumptionListFragment : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initUiState()

        viewModel.consumptionUiState.observe(viewLifecycleOwner){state ->
            Log.i("!!!", "data - ${state.data}")
            Log.i("!!!", "list - ${state.listConsumptions}")
        }
    }

}