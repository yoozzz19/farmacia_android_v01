package com.example.farmaciaDrPerez.models

import com.google.gson.annotations.SerializedName

data class InventorySummary(
    @SerializedName("total_products") val totalProducts: Int,
    @SerializedName("total_value") val totalValue: Double,
    @SerializedName("low_stock_count") val lowStockCount: Int,
    @SerializedName("out_of_stock_count") val outOfStockCount: Int,
    @SerializedName("expiring_soon_count") val expiringSoonCount: Int

)
