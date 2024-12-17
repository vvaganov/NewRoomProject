package com.example.newroomproject.ui.calorieConsumptionList

import com.example.newroomproject.data.user.UserDao
import com.example.newroomproject.di.DataModule.IoDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class CalorieConsumptionListRepository @Inject constructor(
    private val userDao: UserDao,
    @IoDispatcher private val ioDispatcher: CoroutineContext) {

    suspend fun getAllConsumptions(data: String) = withContext(ioDispatcher){
        userDao.getAllConsumptions(data)
    }
}