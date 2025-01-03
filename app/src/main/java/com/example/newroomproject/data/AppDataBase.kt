package com.example.newroomproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newroomproject.data.product.ProductDao
import com.example.newroomproject.data.product.ProductEntity
import com.example.newroomproject.data.user.CalorieSpendEntity
import com.example.newroomproject.data.user.UserDao
import com.example.newroomproject.data.user.UserParamsEntity

@Database(
    entities = [UserParamsEntity::class, CalorieSpendEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}