package com.example.newroomproject.ui.dashBoard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newroomproject.databinding.FragmentDashBoardBinding
import com.example.newroomproject.utils.DateTimeParse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: DashBoardRepository,
) : ViewModel() {

    private val _dashUiState = MutableLiveData(DashBoardUiState())
    val dashUiState: LiveData<DashBoardUiState> get() = _dashUiState

    fun changeToData(selectedData: LocalDateTime) {
        val data = DateTimeParse(selectedData).dataTime().data
        viewModelScope.launch {
            _dashUiState.postValue(
                dashUiState.value?.copy(
                    data = data,
                    metabolism = repository.getFullMetabolismInDay(data).toInt(),
                )
            )
        }
    }

    fun initUiState(saveInstantState: Bundle?) {

        viewModelScope.launch {
            if (saveInstantState != null) {
                _dashUiState.postValue(
                    dashUiState.value?.copy(
                        data = saveInstantState.getString("Key"),
                        metabolism = repository.getFullMetabolismInDay(saveInstantState.getString("Key"))
                            .toInt(),
                    )
                )
            } else {
                val data = DateTimeParse(LocalDateTime.now(ZoneId.systemDefault())).dataTime().data
                _dashUiState.postValue(
                    dashUiState.value?.copy(
                        data = data,
                        metabolism = repository.getFullMetabolismInDay(data).toInt(),
                    )
                )
            }
        }
    }
}

data class DashBoardUiState(
    val data: String? = "",
    val metabolism: Int = 0,
)
