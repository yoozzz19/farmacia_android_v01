package com.example.farmaciaDrPerez.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.farmaciaDrPerez.models.InventorySummary
import com.example.farmaciaDrPerez.models.ProductItem
import com.example.farmaciaDrPerez.view_models.ReportUiState
import com.example.farmaciaDrPerez.view_models.ReportViewModel
import com.example.farmaciaDrPerez.view_models.StockFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(viewModel: ReportViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val filteredList by viewModel.filteredProducts.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val currentFilter by viewModel.currentFilter.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Reporte de Inventario", fontWeight = FontWeight.Bold)
                        Text("Farmacia Dr. Perez", fontSize = 12.sp, fontWeight = FontWeight.Normal)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF375AB4),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { viewModel.loadReport() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Recargar", tint = Color.White)
                    }
                }
            )
        },
        containerColor = Color(0xFFF5F7FA)
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (val state = uiState) {
                is ReportUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is ReportUiState.Error -> ErrorView(state.message) { viewModel.loadReport() }
                is ReportUiState.Success -> {
                    ReportContent(
                        summary = state.data.summary,
                        products = filteredList,
                        query = searchQuery,
                        onQueryChange = viewModel::onSearchQueryChanged,
                        selectedFilter = currentFilter,
                        onFilterChange = viewModel::onFilterChanged
                    )
                }
            }
        }
    }
}

@Composable
fun ErrorView(msg: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red, modifier = Modifier.size(48.dp))
        Text(text = "Error de Conexión", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
        Text(text = msg, color = Color.Gray, modifier = Modifier.padding(8.dp))
        Button(onClick = onRetry) { Text("Reintentar") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportContent(
    summary: InventorySummary,
    products: List<ProductItem>,
    query: String,
    onQueryChange: (String) -> Unit,
    selectedFilter: StockFilter,
    onFilterChange: (StockFilter) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. TARJETAS DE RESUMEN (KPIs)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KpiCard(
                    title = "Valor Total",
                    value = "$${summary.totalValue}",
                    color = Color(0xFF2E7D32), // Verde
                    modifier = Modifier.weight(1f)
                )
                KpiCard(
                    title = "Productos",
                    value = "${summary.totalProducts}",
                    color = Color(0xFF1565C0), // Azul
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KpiCard(
                    title = "Stock Bajo",
                    value = "${summary.lowStockCount}",
                    color = Color(0xFFEF6C00), // Naranja
                    modifier = Modifier.weight(1f)
                )
                KpiCard(
                    title = "Agotados",
                    value = "${summary.outOfStockCount}",
                    color = Color(0xFFC62828), // Rojo
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // 2. SECCIÓN DE FILTROS Y BÚSQUEDA
        stickyHeader {
            Column(modifier = Modifier.background(Color(0xFFF5F7FA)).padding(bottom = 8.dp)) {
                // Buscador
                OutlinedTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Buscar medicamento...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White
                    ),
                    singleLine = true
                )

                // Chips de Filtro
                LazyRow(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item { FilterChipBtn("Todos", selectedFilter == StockFilter.ALL) { onFilterChange(StockFilter.ALL) } }
                    item { FilterChipBtn("⚠️ Stock Bajo", selectedFilter == StockFilter.LOW_STOCK) { onFilterChange(StockFilter.LOW_STOCK) } }
                    item { FilterChipBtn("⛔ Agotados", selectedFilter == StockFilter.OUT_OF_STOCK) { onFilterChange(StockFilter.OUT_OF_STOCK) } }
                }
            }
        }

        // 3. LISTA DE PRODUCTOS
        if (products.isEmpty()) {
            item {
                Text(
                    text = "No se encontraron productos con estos filtros.",
                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            items(products) { product ->
                ProductReportRow(product)
            }
        }
    }
}

@Composable
fun FilterChipBtn(text: String, isSelected: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { Text(text) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Color(0xFF375AB4),
            selectedLabelColor = Color.White
        )
    )
}

@Composable
fun KpiCard(title: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = color)
        }
    }
}

@Composable
fun ProductReportRow(product: ProductItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen
            AsyncImage(
                model = product.imageUrl ?: "https://via.placeholder.com/150",
                contentDescription = null,
                modifier = Modifier.size(48.dp).background(Color.LightGray, RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Info
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.name, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(text = "Código: ${product.code ?: "S/C"}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }

            // Estado y Precio
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "$${product.price}", fontWeight = FontWeight.Bold, color = Color(0xFF375AB4))

                val (color, label, icon) = when(product.status) {
                    "out_of_stock" -> Triple(Color(0xFFC62828), "Agotado", Icons.Default.Close)
                    "low_stock" -> Triple(Color(0xFFEF6C00), "Bajo", Icons.Default.Warning)
                    else -> Triple(Color(0xFF2E7D32), "Normal", Icons.Default.CheckCircle)
                }

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                    Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Stock: ${product.stock}", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = color)
                }
            }
        }
    }
}