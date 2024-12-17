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

@AndroidEntryPoint
class DashBoardFragment : Fragment(), DatePickerFragment.OnDateSetListener {

    private val binding: FragmentDashBoardBinding by lazy {
        FragmentDashBoardBinding.inflate(
            layoutInflater
        )
    }

    private var selected = ""

    private val viewModel: DashBoardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataPucker = DatePickerFragment(this)

            binding.tvCalendar.text = viewModel.getCurrentData()

        viewModel.profileUserState.observe(viewLifecycleOwner) { userParams ->
            with(binding) {
                tvNumberCalorySpend.text = userParams.metabolism.toString()
                tvCalendar.text = userParams.currentData
            }
        }

        binding.tvCalendar.setOnClickListener {
            dataPucker.show(childFragmentManager, "data_fragment")
        }

        binding.btnParishEnergy.setOnClickListener {
            parentFragmentManager.commit {
                replace<CalorieSpendListFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }

        viewModel.initUser(binding.tvCalendar.text.toString())
    }


    override fun onDateSet(date: String) {
        viewModel.changeToData(date)
    }


}