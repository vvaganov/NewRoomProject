package com.example.newroomproject.ui.calorieSpendList

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.newroomproject.databinding.FragmentCalorieConsumptionListBinding
import com.example.newroomproject.R
import dagger.hilt.android.AndroidEntryPoint

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

        val adapter = CalorieSpendListAdapter(emptyList())
        binding.rvCategories.adapter = adapter

        viewModel.initUiState()

        viewModel.consumptionUiState.observe(viewLifecycleOwner){state ->
            if (true) {
                binding.tvConsListTitle.text = state.data
                adapter.dataSet = state.listConsumptions
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), getString(R.string.load_error), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnSaveCons.setOnClickListener{
            viewModel.insertCalorieConsumption(
                binding.inputConsumpCallorie.text.toString().toInt()
            )
        }
        binding.btnBackToDashboard.setOnClickListener{
            parentFragmentManager.popBackStack()
        }


    }
}