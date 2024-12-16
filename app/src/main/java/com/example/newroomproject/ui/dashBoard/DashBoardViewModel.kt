package com.example.newroomproject.ui.dashBoard

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newroomproject.Converters
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
            val params = repository.getLastUserParams()
            Log.i("!!!", "fragment - $params")
            val format = "dd.MM.yyyy"
            _profileUserState.postValue(
                profileUserState.value?.copy(
                    data = converters.timestampToString(params.data, format),
                    name = params.name,
                    weight = params.weight,
                    height = params.height,
                    age = converters.getFullYears(params.dateOfBirth).toString(),
                    gender = params.gender
                )
            )
        }
    }
}