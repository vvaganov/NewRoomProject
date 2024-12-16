package com.example.newroomproject.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_params")
data class UserParamsEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "user_name")
    val name: String,
    val weight: Double,
    val height: Int,
    val dateOfBirth: Long,
    val gender: String
)

