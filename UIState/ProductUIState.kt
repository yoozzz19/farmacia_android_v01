package com.example.farmaciaDrPerez.UIState

import com.example.farmaciaDrPerez.responses.ProductResponse

data class ProductUIState (
    val products: List<ProductResponse> = emptyList(),

    val isLoading: Boolean = false,

    val errorMessage: String? = null
){
}