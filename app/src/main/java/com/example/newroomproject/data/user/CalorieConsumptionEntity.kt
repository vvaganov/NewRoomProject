package com.example.newroomproject.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calorie_cons")
data class CalorieConsumptionEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dataTime: Long,
    val value: Int
)