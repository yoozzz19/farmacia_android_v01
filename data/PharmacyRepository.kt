package com.example.farmaciaDrPerez.data


import com.example.farmaciaDrPerez.models.Category
import com.example.farmaciaDrPerez.models.Product
import com.example.farmaciaDrPerez.models.Supplier
import com.example.farmaciaDrPerez.requests.ProductRequest
import com.example.farmaciaDrPerez.responses.CategoryResponse
import com.example.farmaciaDrPerez.responses.InventoryResponse
import com.example.farmaciaDrPerez.responses.ProductResponse

import com.example.farmaciaDrPerez.responses.SupplierResponse

object PharmacyRepository {

    private val Api = PharmacyInstance.API

    fun ProductResponse.toProduct(): Product {

        return Product(
            id = this.id,
            codigo = this.codigo,
            name = this.name,
            presentation = this.presentation,
            purchase_price = this.purchase_price,
            sale_price = this.sale_price,
            location = this.location,
            min_stock = this.min_stock,
            max_stock = this.max_stock,
            description = this.description,
            category_id = this.category_id,
            supplier_id= this.supplier_id
        )
    }
    suspend fun addProducts(product : ProductRequest): Product {
        val response = Api.addProduct(product)
        return response.toProduct()
    }
    suspend fun getCategories(): List<Category>{
        val response : CategoryResponse = Api.getCategory()
        return response.categories
    }
    suspend fun getSupplier(): List<Supplier>{
        val response : SupplierResponse= Api.getSuppliers()
        return response.suppliers

    }
    suspend fun getReport(): Result<InventoryResponse> {
        return try {
            val response = Api.getInventoryReport()
            if (response.success) {
                Result.success(response)
            } else {
                Result.failure(Exception("La API devolvió éxito=falso"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}