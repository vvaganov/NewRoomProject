package com.example.newroomproject

import androidx.lifecycle.ViewModel
import com.example.newroomproject.data.user.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val userParamsDao: UserDao) : ViewModel() {

    suspend fun getRowCount() = withContext(Dispatchers.IO) {
        userParamsDao.getRowCountUserParams() > 0
    }
}