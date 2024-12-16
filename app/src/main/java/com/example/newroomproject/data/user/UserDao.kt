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
}