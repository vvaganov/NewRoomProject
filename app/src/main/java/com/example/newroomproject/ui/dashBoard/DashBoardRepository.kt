package com.example.newroomproject.ui.dashBoard

import android.util.Log
import com.example.newroomproject.Converters
import com.example.newroomproject.data.user.UserDao
import com.example.newroomproject.di.DataModule.IoDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DashBoardRepository @Inject constructor(
    private val params: UserDao,
    private val converters: Converters,
    @IoDispatcher private val ioDispatcher: CoroutineContext
) {

    suspend fun getLastUserParams() = withContext(ioDispatcher) {
        Log.i("!!!", "fragment - ${params.getLastParams()}")
        params.getLastParams()
    }

}

data class UserParams(
    val id: Long = 0,
    val data: String = "",
    val name: String = "",
    val weight: Double = 0.0,
    val height: Int = 0,
    val age: String = "",
    val gender: String = ""
)