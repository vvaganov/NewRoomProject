package com.example.newroomproject.data.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val proteins: Double,
    val fats: Double,
    val carbohydrates: Double,
    val fiber: Double
){
    fun mapToProduct(){
        Product(
            id = id,
            name = name,
            proteins = proteins,
            fats = fats,
            carbohydrates = carbohydrates,
            fiber = fiber
        )
    }
}

data class Product(
    val id: Int? = null,
    val name: String,
    val proteins: Double,
    val fats: Double,
    val carbohydrates: Double,
    val fiber: Double
)
