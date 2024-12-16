package com.example.newroomproject.ui.dashBoard

import android.annotation.SuppressLint
import android.util.Log
import com.example.newroomproject.utils.Converters
import com.example.newroomproject.data.user.UserDao
import com.example.newroomproject.di.DataModule.IoDispatcher
import com.example.newroomproject.model.BasicMetabolism
import com.example.newroomproject.model.BasicMetabolism.HarrisBenedictMetabolism
import kotlinx.coroutines.Dispatchers

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
    suspend fun getMetabolism() = withContext(Dispatchers.IO){
        val params = params.getLastParams()
        val userParams = ParamsMap(
            weight = params.weight,
            height = params.height,
            age = converters.getFullYears(params.dateOfBirth),
            gender = params.gender
        )
        metabolism.getBasicMetabolism(userParams)
    }
}

data class ParamsMap(
    val weight: Double,
    val height: Int,
    val age: Int,
    val gender: String
)


