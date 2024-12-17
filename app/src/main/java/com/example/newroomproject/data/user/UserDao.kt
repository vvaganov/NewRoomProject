package com.example.newroomproject.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUserParams(userParams: UserParamsEntity)

    @Query("SELECT COUNT(*) FROM USER_PARAMS")
    suspend fun getRowCountUserParams(): Int

    @Query("SELECT * FROM user_params ORDER BY data DESC LIMIT 1")
    suspend fun getLastParams() : UserParamsEntity

    @Insert
    suspend fun insertCalorieConsumption(value: CalorieConsumptionEntity)

    @Query("SELECT * FROM CALORIE_CONS WHERE data = :data")
    suspend fun getAllConsumptions(data: String) : List<CalorieConsumptionEntity>
}