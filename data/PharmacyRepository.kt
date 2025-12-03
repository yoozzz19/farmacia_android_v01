package com.example.farmaciaDrPerez.data


import com.example.farmaciaDrPerez.models.Product
import com.example.farmaciaDrPerez.requests.ProductRequest
import com.example.farmaciaDrPerez.responses.ProductResponse

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
            stock = this.stock,
            location = this.location,
            min_stock = this.min_stock,
            max_stock = this.max_stock,
            description = this.description,
            image = this.image,
            category_id = this.category_id,
            supplier_id= this.supplier_id
        )
    }
    suspend fun addProducts(product : ProductRequest): Product {
        val response = Api.addProduct(product)
        return response.toProduct()
    }
}