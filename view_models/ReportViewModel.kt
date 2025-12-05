package com.example.farmaciaDrPerez.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmaciaDrPerez.data.PharmacyRepository
import com.example.farmaciaDrPerez.models.ProductItem
import com.example.farmaciaDrPerez.responses.InventoryResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch



// Estados de la Carga de Datos
sealed interface ReportUiState {
    object Loading : ReportUiState
    data class Success(val data: InventoryResponse) : ReportUiState
    data class Error(val message: String) : ReportUiState
}

// Tipos de Filtro
enum class StockFilter { ALL, LOW_STOCK, OUT_OF_STOCK }

class ReportViewModel: ViewModel() {
    private val repository = PharmacyRepository

    // Estado crudo de la API
    private val _rawState = MutableStateFlow<ReportUiState>(ReportUiState.Loading)
    val uiState: StateFlow<ReportUiState> = _rawState

    // Estados de los Filtros
    val searchQuery = MutableStateFlow("")
    val currentFilter = MutableStateFlow(StockFilter.ALL)

    // Lista Filtrada (Calculada automáticamente cuando cambia la búsqueda, el filtro o los datos)
    val filteredProducts: StateFlow<List<ProductItem>> = combine(
        _rawState,
        searchQuery,
        currentFilter
    ) { state, query, filter ->
        if (state is ReportUiState.Success) {
            state.data.products.filter { product ->
                // 1. Filtro de Texto
                val matchesSearch = product.name.contains(query, ignoreCase = true) ||
                        (product.code?.contains(query, ignoreCase = true) == true)

                // 2. Filtro de Estado (Chips)
                val matchesStatus = when (filter) {
                    StockFilter.ALL -> true
                    StockFilter.LOW_STOCK -> product.status == "low_stock"
                    StockFilter.OUT_OF_STOCK -> product.status == "out_of_stock"
                }

                matchesSearch && matchesStatus
            }
        } else {
            emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        loadReport()
    }

    fun loadReport() {
        viewModelScope.launch {
            _rawState.value = ReportUiState.Loading
            val result = repository.getReport()
            result.onSuccess {
                _rawState.value = ReportUiState.Success(it)
            }.onFailure {
                _rawState.value = ReportUiState.Error(it.message ?: "Error desconocido")
            }
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        searchQuery.value = newQuery
    }

    fun onFilterChanged(newFilter: StockFilter) {
        currentFilter.value = newFilter
    }

}