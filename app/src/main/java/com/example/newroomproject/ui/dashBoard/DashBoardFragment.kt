package com.example.newroomproject.ui.dashBoard

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.example.newroomproject.ui.calorieConsumptionList.CalorieConsumptionListFragment

@AndroidEntryPoint
class DashBoardFragment : Fragment(){

    private val binding: FragmentDashBoardBinding by lazy {
        FragmentDashBoardBinding.inflate(
            layoutInflater
        )
    }

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

        viewModel.initUser()

        viewModel.profileUserState.observe(viewLifecycleOwner) { userParams ->
            with(binding) {
                tvNumberEnergyParish.text = userParams.metabolism.toString()
            }
        }

        binding.btnParishEnergy.setOnClickListener{
            parentFragmentManager.commit{
                replace<CalorieConsumptionListFragment>(R.id.fragment_container_view)
            }
        }

//        binding.btnParishEnergy.setOnClickListener{
//            val dialog = ConsumptionDialogFragment{ inputText ->
//                viewModel.insertCalorieConsumption(inputText)
//            }
//            dialog.show(parentFragmentManager, "ConsumptionDialogFragment")
//
//        }
    }

}