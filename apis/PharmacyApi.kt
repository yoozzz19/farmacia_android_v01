package com.example.farmaciaDrPerez.apis

import com.example.farmaciaDrPerez.responses.PostProduct
import com.example.farmaciaDrPerez.responses.ProductResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PharmacyApi {

    @POST("api/products/register")
    suspend fun addProduct(@Body product : PostProduct): ProductResponse
}