package com.example.farmaciaDrPerez.view_models


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmaciaDrPerez.UIState.ProductUIState
import com.example.farmaciaDrPerez.data.PharmacyRepository
import com.example.farmaciaDrPerez.models.Product

import com.example.farmaciaDrPerez.requests.ProductRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProductViewModel : ViewModel() {
    private val  _uiState = MutableStateFlow(ProductUIState())
    val uiState: StateFlow<ProductUIState> = _uiState.asStateFlow()
    val categoriesList: StateFlow<List<Category>> = MutableStateFlow(emptyList())
    val suppliersList: StateFlow<List<Supplier>> = MutableStateFlow(emptyList())
    val repo = PharmacyRepository

    private fun ProductUIState.toProductRequest(): ProductRequest{
        return ProductRequest(
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

    fun createProduct(){
        viewModelScope.launch {

            try {
                val request : ProductRequest = _uiState.value.toProductRequest()
                val newProduct: Product = repo.addProducts(request)


            }catch (e:Exception){

            }
        }
    }
    fun onValueCode(codigo:String){
        _uiState.value = _uiState.value.copy(codigo=codigo)
    }
    fun onValueName(name:String){
        _uiState.value = _uiState.value.copy(name=name)
    }
    fun onValuePresentation(presentation: String){
        _uiState.value = _uiState.value.copy(presentation=presentation)
    }
    fun onValuePurchase(purchase:String){
        var Purchase = purchase.toDoubleOrNull()?:0.0
        _uiState.value = _uiState.value.copy(purchase_price = Purchase)
    }
    fun onValueSale(sale:String){
        var Sale = sale.toDoubleOrNull()?:0.0
        _uiState.value = _uiState.value.copy(sale_price = Sale)
    }
    fun onValueStock(stock:String){
        var Stock = stock.toIntOrNull()?:0
        _uiState.value = _uiState.value.copy(stock = Stock)
    }
    fun onValueLocation(location: String){
        _uiState.value = _uiState.value.copy(location=location)
    }

    fun onValueMinStock(minStock:String){
        var MinStock = minStock.toIntOrNull()?:0
        _uiState.value = _uiState.value.copy(min_stock = MinStock)
    }
    fun onValueMaxStock(maxStock:String){
        var MaxStock = maxStock.toIntOrNull()?:0
        _uiState.value = _uiState.value.copy(max_stock = MaxStock)
    }
    fun onValueDescription(description: String){
        _uiState.value = _uiState.value.copy(description=description)
    }
    fun onValueImage(image: String){
        _uiState.value = _uiState.value.copy(description=image)
    }
    fun onValueCategory(category: String){
        var Category = category.toIntOrNull()?:0
        _uiState.value = _uiState.value.copy(category_id = Category)
    }
    fun onValueSupplier(supplier: String){
        var Supplier = supplier.toIntOrNull()?:0
        _uiState.value = _uiState.value.copy(supplier_id = Supplier)
    }


}