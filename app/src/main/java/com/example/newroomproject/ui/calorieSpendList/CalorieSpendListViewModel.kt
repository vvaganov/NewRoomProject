package com.example.newroomproject.ui.calorieSpendList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newroomproject.data.user.CalorieSpendEntity
import com.example.newroomproject.databinding.FragmentCalorieConsumptionListBinding
import com.example.newroomproject.databinding.FragmentDashBoardBinding
import com.example.newroomproject.utils.Converters

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalorieConsumptionListViewModel @Inject constructor(
    private val repository: CalorieSpendListRepository,
    private val converters: Converters
) : ViewModel() {

//    private val _consumptionUiState = MutableLiveData(CalorieConsumptionListUiState())
//    val consumptionUiState: LiveData<CalorieConsumptionListUiState> get() = _consumptionUiState

    fun initUiState(data: String, binding: FragmentCalorieConsumptionListBinding) {
        viewModelScope.launch{
            val uiState = CalorieConsumptionListUiState(
                data =  data,
                listConsumptions = repository.getAllConsumptions(data) ?: emptyList()
            )
            uiState.update(binding, data)
        }
    }

    fun insertCalorieConsumption(value: Int, data: String, time: String) {
        viewModelScope.launch {
            repository.insertCalorieConsumption(value, data, time)
        }
    }
}


data class CalorieConsumptionListUiState(
    val data: String,
    val listConsumptions: List<CalorieSpendEntity>
) {
    fun update(binding: FragmentCalorieConsumptionListBinding, data: String) {
        val adapter = CalorieSpendListAdapter(emptyList())
        binding.rvSpendList.adapter = adapter
        adapter.dataSet = listConsumptions
        binding.tvConsListTitle.text = data

    }
}

