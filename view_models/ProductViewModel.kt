package com.example.farmaciaDrPerez.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmaciaDrPerez.data.PharmacyRepository
import com.example.farmaciaDrPerez.responses.PostProduct
import com.example.farmaciaDrPerez.responses.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProductViewModel : ViewModel() {
    private val repository = PharmacyRepository
    private val _products = MutableStateFlow<List<ProductResponse>>(emptyList())
    val products: StateFlow<List<ProductResponse>> = _products
    private val _itsLoading = MutableStateFlow(false)
    val itsLoading: StateFlow<Boolean> = _itsLoading


    fun addProducts(newProduct: PostProduct) {
        viewModelScope.launch {
            _itsLoading.value = true
            try {
                val addedProduct = repository.addProducts(newProduct)
                _products.update { currentList ->
                    currentList + addedProduct
                }
            } catch (e: Exception) {

            } finally {
                _itsLoading.value = false
            }
        }
    }
}