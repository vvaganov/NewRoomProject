package com.example.newroomproject.ui.product

import com.example.newroomproject.data.product.ProductDao
import com.example.newroomproject.data.product.ProductEntity
import com.example.newroomproject.di.DataModule.IoDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AddProductRepository @Inject constructor(
    private val productDao: ProductDao,
    @IoDispatcher private val ioDispatcher: CoroutineContext
) {

    suspend fun insertProduct(product:ProductEntity) = withContext(ioDispatcher){
        productDao.insertProduct(product)
    }
}