package com.example.farmaciaDrPerez.data

import com.example.farmaciaDrPerez.responses.PostProduct
import com.example.farmaciaDrPerez.responses.ProductResponse
import retrofit2.Retrofit

object PharmacyRepository {

    private val Api = PharmacyInstance.API

    suspend fun addProducts(product : PostProduct): ProductResponse {
        val response = Api.addProduct(product)
        return response
    }
}