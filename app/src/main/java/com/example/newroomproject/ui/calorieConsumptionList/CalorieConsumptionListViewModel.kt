package com.example.newroomproject.ui.calorieConsumptionList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newroomproject.data.user.CalorieConsumptionEntity
import com.example.newroomproject.utils.Converters

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalorieConsumptionListViewModel @Inject constructor(
    private val repository: CalorieConsumptionListRepository,
    private val converters: Converters
): ViewModel() {

    private val _consumptionUiState = MutableLiveData(CalorieConsumptionListUiState())
    val consumptionUiState: LiveData<CalorieConsumptionListUiState> get() = _consumptionUiState

    fun initUiState(){
        viewModelScope.launch{
            val data = System.currentTimeMillis()
            _consumptionUiState.postValue(
                consumptionUiState.value?.copy(
                    data = converters.timestampToString(data),
                    listConsumptions = repository.getAllConsumptions(converters.timestampToString(data))
                )
            )
        }
    }
}
data class CalorieConsumptionListUiState(
    val data: String = "",
    val listConsumptions: List<CalorieConsumptionEntity>? = emptyList()
)

