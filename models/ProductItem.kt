package com.example.farmaciaDrPerez.models

import com.google.gson.annotations.SerializedName

data class ProductItem(
    val id: Int,
    val code: String?,
    val name: String,
    val stock: Int,
    val price: Double,
    val status: String,
    @SerializedName("image_url") val imageUrl: String?

)
