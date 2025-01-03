package com.example.newroomproject.di

import android.content.Context
import com.example.newroomproject.utils.Converters
import com.example.newroomproject.data.AppDatabase
import com.example.newroomproject.data.product.ProductDao
import com.example.newroomproject.data.user.UserDao
import com.example.newroomproject.model.BasicMetabolism
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class IoDispatcher

    @IoDispatcher
    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineContext = Dispatchers.IO

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    fun provideUserParamsDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao = appDatabase.productDao()

    @Provides
    fun providesConverter(): Converters = Converters()


}