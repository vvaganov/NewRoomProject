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
    ): ViewModel() {

    private val _profileUserState = MutableLiveData(UserParams())
    val profileUserState: LiveData<UserParams> get() = _profileUserState

    @SuppressLint("NewApi")
    fun initUser(){
        viewModelScope.launch{
            _profileUserState.postValue(
                profileUserState.value?.copy(
                    metabolism = repository.getMetabolism().basicMetabolism.toInt()
                )
            )
        }
    }
}

data class UserParams(
 val metabolism: Int = 0
)