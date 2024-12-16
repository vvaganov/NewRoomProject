package com.example.newroomproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.newroomproject.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application).userDao()

    suspend fun getRowCount() = withContext(Dispatchers.IO) {
        db.getRowCountUserParams() > 0
    }
}