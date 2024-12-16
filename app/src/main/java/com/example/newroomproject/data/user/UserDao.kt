package com.example.newroomproject.data.user

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDao {

    @Insert
    suspend fun insertUserParams(userParams: UserParamsEntity)
}