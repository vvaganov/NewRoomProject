package com.example.newroomproject.ui.dashBoard

import android.annotation.SuppressLint
import com.example.newroomproject.data.user.CalorieConsumptionEntity
import com.example.newroomproject.utils.Converters
import com.example.newroomproject.data.user.UserDao
import com.example.newroomproject.model.BasicMetabolism
import com.example.newroomproject.model.BasicMetabolism.HarrisBenedictMetabolism
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DashBoardRepository @Inject constructor(
    private val params: UserDao,

    private val converters: Converters,
) {

    private val metabolism: BasicMetabolism = HarrisBenedictMetabolism()

    @SuppressLint("NewApi")
    suspend fun getMetabolism() = withContext(Dispatchers.IO) {
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

    suspend fun insertCalorieConsumption(value: Int) = withContext(Dispatchers.IO) {
        params.insertCalorieConsumption(
            CalorieConsumptionEntity(
                dataTime = System.currentTimeMillis(),
                value = value,
            )
        )
    }
}

data class ParamsMap(
    val weight: Double,
    val height: Int,
    val age: Int,
    val gender: String
)


