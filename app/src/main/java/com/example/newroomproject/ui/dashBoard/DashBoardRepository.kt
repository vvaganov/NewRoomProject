package com.example.newroomproject.ui.dashBoard

import android.annotation.SuppressLint
import com.example.newroomproject.utils.Converters
import com.example.newroomproject.data.user.UserDao
import com.example.newroomproject.di.DataModule.IoDispatcher
import com.example.newroomproject.model.BasicMetabolism
import com.example.newroomproject.model.BasicMetabolism.HarrisBenedictMetabolism
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DashBoardRepository @Inject constructor(
    private val params: UserDao,
    private val converters: Converters,
    @IoDispatcher private val ioDispatcher: CoroutineContext
) {

    private val metabolism: BasicMetabolism = HarrisBenedictMetabolism()

    @SuppressLint("NewApi")
    suspend fun getMetabolism() = withContext(ioDispatcher) {
        val params = params.getLastParams()
        metabolism.getBasicMetabolism(
            ParamsMap(
                weight = params.weight,
                height = params.height,
                age = converters.getFullYears(params.dateOfBirth),
                gender = params.gender
            )
        )
    }

    suspend fun getFullMetabolismInDay(data: String?) = withContext(ioDispatcher){
        val expensesList = params.getAllConsumptions(data) ?: emptyList()
        val expensesSumInDay = expensesList.sumOf { it.value }
        getMetabolism().basicMetabolism + expensesSumInDay
    }
}

data class ParamsMap(
    val weight: Double,
    val height: Int,
    val age: Int,
    val gender: String
)


