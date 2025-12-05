package com.example.farmaciaDrPerez.responses

import com.example.farmaciaDrPerez.models.Category

data class CategoryResponse(
    val categories : List<Category> = emptyList()
) {
}