package com.example.newroomproject.ui.dashBoard

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newroomproject.model.Metabolism
import com.example.newroomproject.utils.Converters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: DashBoardRepository,
    private val converters: Converters
) : ViewModel() {

    private val _profileUserState = MutableLiveData(DashBoardUiState())
    val profileUserState: LiveData<DashBoardUiState> get() = _profileUserState


    @SuppressLint("NewApi")
    fun initUser(data: String) {
        viewModelScope.launch {
            _profileUserState.postValue(
                profileUserState.value?.copy(
                    metabolism = repository.getFullMetabolismInDay(data).toInt(),
                    currentData = data
                )
            )
        }
    }

    fun getCurrentData(): String {
        return converters.timestampToString(System.currentTimeMillis())
    }

    fun changeToData(data: String) {
        viewModelScope.launch {
            _profileUserState.postValue(
                profileUserState.value?.copy(
                    metabolism = repository.getFullMetabolismInDay(data).toInt(),
                    currentData = data
                )
            )
        }
    }
}

data class DashBoardUiState(
    val metabolism: Int = 0,
    val currentData: String = ""
)