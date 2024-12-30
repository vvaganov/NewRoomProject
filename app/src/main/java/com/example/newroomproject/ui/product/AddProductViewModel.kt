package com.example.newroomproject.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newroomproject.data.product.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val repository: AddProductRepository
) : ViewModel() {

    private val _addProductUiState = MutableLiveData(AddProductUiState())
    val addProductUiState: LiveData<AddProductUiState> = _addProductUiState


    fun insertProduct(product: Product){
        viewModelScope.launch {
            repository.insertProduct(product.mapToProductEntity())
        }
    }
}

data class AddProductUiState(
    val productList: List<Product> = emptyList()
)