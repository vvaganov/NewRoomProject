package com.example.newroomproject.ui.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newroomproject.data.user.UserDao
import com.example.newroomproject.data.user.UserParamsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val userParamsDao: UserDao
): ViewModel(){

    fun insertUserParams(userParams: UserParamsEntity) {
        viewModelScope.launch{
            userParamsDao.insertUserParams(userParams)
        }
    }
}