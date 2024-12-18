package com.example.newroomproject.ui.dashBoard

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newroomproject.databinding.FragmentDashBoardBinding
import com.example.newroomproject.utils.Converters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: DashBoardRepository,
    private val converters: Converters,
) : ViewModel() {

    fun changeToData(data: String, binding: FragmentDashBoardBinding) {
        viewModelScope.launch {
            val state = DashBoardUiState(
                metabolism = repository.getFullMetabolismInDay(data).toInt(),
            )
            state.updateBinding(binding)
        }
    }

    fun initUiState(data: String, binding: FragmentDashBoardBinding) {
        viewModelScope.launch{
            val state = DashBoardUiState(
                metabolism = repository.getFullMetabolismInDay(data).toInt(),
            )
            state.updateBinding(binding)

        }
    }
}

data class DashBoardUiState(
    val metabolism: Int = 0,
){
    @SuppressLint("SetTextI18n")
    fun updateBinding(binding: FragmentDashBoardBinding){
        binding.tvNumberCalorySpend.text = metabolism.toString()
    }
}