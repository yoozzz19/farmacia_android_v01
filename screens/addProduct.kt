package com.example.farmaciaDrPerez.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmaciaDrPerez.components.header
import com.example.farmaciaDrPerez.view_models.ProductViewModel


@Composable
fun addProductScreen(
    modifier: Modifier= Modifier,
    viewModel: ProductViewModel = viewModel()
)
{
    val state = viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val blue = Color(0xFF1D35C4)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        header(logo="F", name="Agregar Producto", color = blue)
        Column (
            modifier = Modifier
                .padding(40.dp)
                .height(450.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedTextField(
                label = { Text("Codigo") },
                value=state.value.codigo,
                onValueChange = {viewModel.onValueCode(it)},
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                label = { Text("Nombre") },
                value=state.value.name,
                onValueChange = {viewModel.onValueName(it)},
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                label = { Text("Presentación") },
                value=state.value.presentation,
                onValueChange = {viewModel.onValuePresentation(it)},
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                label = { Text("Precio de Compra") },
                value=state.value.purchase_price.toString(),
                onValueChange = {viewModel.onValuePurchase(it)},
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                label = { Text("Precio de Venta") },
                value=state.value.sale_price.toString(),
                onValueChange = {viewModel.onValueSale(it)},
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                label = { Text("Ubicación") },
                value=state.value.location,
                onValueChange = {viewModel.onValueLocation(it)},
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                label = { Text("Stock minimo") },
                value=state.value.min_stock.toString(),
                onValueChange = {viewModel.onValueMinStock(it)},
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                label = { Text("Stock máximo") },
                value=state.value.max_stock.toString(),
                onValueChange = {viewModel.onValueMaxStock(it)},
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                label = { Text("Descripción") },
                value=state.value.description,
                onValueChange = {viewModel.onValueDescription(it)},
                shape = RoundedCornerShape(16.dp)
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

        }
            Button(
                onClick = {
                    viewModel.createProduct()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue
                )
            ) { Text("POST")}


    }
}

//@Composable
//fun DropdownSelector{



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun screenPreview(){
    addProductScreen()
}

