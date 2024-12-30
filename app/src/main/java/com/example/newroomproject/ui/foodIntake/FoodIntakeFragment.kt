package com.example.newroomproject.ui.foodIntake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.newroomproject.R
import com.example.newroomproject.databinding.FragmentFoodIntakeBinding
import com.example.newroomproject.ui.product.AddProductFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodIntakeFragment : Fragment() {

    private var _binding: FragmentFoodIntakeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodIntakeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddProduct.setOnClickListener {
            parentFragmentManager.commit {
                replace<AddProductFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }
    }
}