package com.example.newroomproject.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calorie_cons")
data class CalorieSpendEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val data: String,
    val time: String,
    val value: Int
)