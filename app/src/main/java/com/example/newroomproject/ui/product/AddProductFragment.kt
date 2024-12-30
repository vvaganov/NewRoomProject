package com.example.newroomproject.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newroomproject.data.product.Product
import com.example.newroomproject.databinding.FragmentAddProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: AddProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveProduct.setOnClickListener {
            viewModel.insertProduct(
                Product(
                    name = binding.inputNameProduct.text.toString(),
                    proteins = binding.inputProtein.text.toString().toDouble(),
                    fats = binding.inputFats.text.toString().toDouble(),
                    carbohydrates = binding.inputCarbohydrate.text.toString().toDouble(),
                    fiber = binding.inputFiber.text.toString().toDouble()
                )
            )
        }
    }
}