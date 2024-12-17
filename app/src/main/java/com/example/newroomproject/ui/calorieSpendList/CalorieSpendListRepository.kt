package com.example.newroomproject.ui.calorieSpendList

import com.example.newroomproject.data.user.CalorieSpendEntity
import com.example.newroomproject.data.user.UserDao
import com.example.newroomproject.di.DataModule.IoDispatcher
import com.example.newroomproject.utils.Converters
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class CalorieSpendListRepository @Inject constructor(
    private val userDao: UserDao,
    @IoDispatcher private val ioDispatcher: CoroutineContext,
    private val converters: Converters) {

    suspend fun getAllConsumptions(data: String) = withContext(ioDispatcher){
        userDao.getAllConsumptions(data)
    }

    suspend fun insertCalorieConsumption(value: Int) = withContext(ioDispatcher) {
        val currentTimesMills = System.currentTimeMillis()
        val dataTime = converters.timestampToDateTimeObject(currentTimesMills)
        userDao.insertCalorieConsumption(
            CalorieSpendEntity(
                data = dataTime.data,
                time = dataTime.time,
                value = value,
            )
        )
    }
}