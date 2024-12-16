package com.example.newroomproject.ui.startScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newroomproject.data.AppDatabase

import com.example.newroomproject.data.user.UserParamsEntity
import kotlinx.coroutines.launch

class StartScreenViewModel(application: Application): AndroidViewModel(application){

    private val db = AppDatabase.getInstance(application).userDao()

    fun insertUserParams(userParams: UserParamsEntity) {
        viewModelScope.launch{
            db.insertUserParams(userParams)
        }

    }
}