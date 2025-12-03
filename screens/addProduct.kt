package com.example.farmaciaDrPerez.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmaciaDrPerez.components.header
import com.example.farmaciaDrPerez.view_models.ProductViewModel


@Composable
fun addProductScreen(
    modifier: Modifier= Modifier,
    viewModel: ProductViewModel
)
{
    val state = viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val blue = Color(0xFF1D35C4)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        header(name="Farmacia Dr. Perez", color = blue)
        Column (
            modifier = Modifier
                .padding(50.dp)
                .verticalScroll(scrollState)
        ) {

            OutlinedTextField(
                label = { Text("Codigo") },
                value=state.value.codigo,
                onValueChange = {viewModel.onValueCode(it)}
            )
            OutlinedTextField(
                label = { Text("Nombre") },
                value=state.value.name,
                onValueChange = {viewModel.onValueName(it)}
            )
            OutlinedTextField(
                label = { Text("Presentación") },
                value=state.value.presentation,
                onValueChange = {viewModel.onValuePresentation(it)}
            )
            OutlinedTextField(
                label = { Text("Precio de Compra") },
                value=state.value.purchase_price.toString(),
                onValueChange = {viewModel.onValuePurchase(it)}
            )
            OutlinedTextField(
                label = { Text("Precio de Venta") },
                value=state.value.sale_price.toString(),
                onValueChange = {viewModel.onValueSale(it)}
            )
            OutlinedTextField(
                label = { Text("Stock") },
                value=state.value.stock.toString(),
                onValueChange = {viewModel.onValueStock(it)}
            )
            OutlinedTextField(
                label = { Text("Ubiación") },
                value=state.value.location,
                onValueChange = {viewModel.onValueLocation(it)}
            )
            OutlinedTextField(
                label = { Text("Stock minimo") },
                value=state.value.min_stock.toString(),
                onValueChange = {viewModel.onValueMinStock(it)}
            )
            OutlinedTextField(
                label = { Text("Stock máximo") },
                value=state.value.max_stock.toString(),
                onValueChange = {viewModel.onValueMaxStock(it)}
            )
            OutlinedTextField(
                label = { Text("Descripción") },
                value=state.value.description,
                onValueChange = {viewModel.onValueDescription(it)}
            )
            OutlinedTextField(
                label = { Text("Imagén") },
                value=state.value.image,
                onValueChange = {viewModel.onValueImage(it)}
            )
            OutlinedTextField(
                label = { Text("ID Categoría") },
                value=state.value.category_id.toString(),
                onValueChange = {viewModel.onValueCategory(it)}
            )
            OutlinedTextField(
                label = { Text("ID Proveedor") },
                value=state.value.supplier_id.toString(),
                onValueChange = {viewModel.onValueSupplier(it)}
            )
            Button(
                onClick = {
                    viewModel.createProduct()
                }
            ) { Text("POST")}
        }

    }
}

