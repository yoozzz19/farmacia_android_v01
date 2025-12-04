package com.example.farmaciaDrPerez.apis


import androidx.core.util.Supplier
import com.example.farmaciaDrPerez.requests.ProductRequest
import com.example.farmaciaDrPerez.responses.CategoryResponse
import com.example.farmaciaDrPerez.responses.ProductResponse
import com.example.farmaciaDrPerez.responses.SupplierResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PharmacyApi {
    @POST("api/products/register")
    suspend fun addProduct(@Body product : ProductRequest): ProductResponse

    @GET("api/products/suppliers")
    suspend fun getSuppliers(): SupplierResponse

    @GET("api/products/category")
    suspend fun getCategory(): CategoryResponse
}