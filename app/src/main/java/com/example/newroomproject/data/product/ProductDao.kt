package com.example.newroomproject.data.product

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(product: ProductEntity)
}