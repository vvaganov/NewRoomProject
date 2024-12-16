package com.example.newroomproject.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "power_cons")
data class PowerConsumptionEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val dataTime: Long,
    val value: Int
)