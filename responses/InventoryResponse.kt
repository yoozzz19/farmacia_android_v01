package com.example.farmaciaDrPerez.responses

import com.example.farmaciaDrPerez.models.InventorySummary
import com.example.farmaciaDrPerez.models.ProductItem
import com.google.gson.annotations.SerializedName

data class InventoryResponse(
    val success: Boolean,
    @SerializedName("generated_at") val generatedAt: String,
    val summary: InventorySummary,
    val products: List<ProductItem>

)
