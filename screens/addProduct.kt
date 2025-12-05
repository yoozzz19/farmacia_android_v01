package com.example.farmaciaDrPerez.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmaciaDrPerez.components.header
import com.example.farmaciaDrPerez.models.Category
import com.example.farmaciaDrPerez.models.Supplier
import com.example.farmaciaDrPerez.view_models.ProductViewModel


@Composable
fun addProductScreen(
    modifier: Modifier= Modifier,
    viewModel: ProductViewModel = viewModel()
)
{
    val state = viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val categories = viewModel.categoriesList.collectAsState()
    val suppliers = viewModel.suppliersList.collectAsState()

    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var selectedSupplier by remember { mutableStateOf<Supplier?>(null) }
    LaunchedEffect(Unit) {
        viewModel.getCategories()
        viewModel.getSuppliers()
    }
    LaunchedEffect(categories.value) {

        if (categories.value.isNotEmpty() && selectedCategory == null) {

            val defaultCategory = categories.value.first()
            selectedCategory = defaultCategory

            viewModel.onValueCategory(defaultCategory.id)
        }
        if (suppliers.value.isNotEmpty() && selectedSupplier == null) {
            val defaultSupplier = suppliers.value.first()
            selectedSupplier = defaultSupplier
            viewModel.onValueSupplier(defaultSupplier.id)
        }
    }

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
                .weight(1f)
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
            categoryDropdown(
                categories=categories.value,
                selectedCategory = selectedCategory,
                onCategorySelected = {
                    selectedCategory=it
                    viewModel.onValueCategory(it.id)
                }
            )
            supplierDropdown(
                suppliers=suppliers.value,
                selectedSupplier=selectedSupplier,
                onSupplierSelected = {
                    selectedSupplier=it
                    viewModel.onValueSupplier(it.id)
                }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun categoryDropdown(
    categories : List<Category>,
    selectedCategory: Category?,
    onCategorySelected : (Category)-> Unit
){
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedCategory?.name ?: "Selecciona una categoría",
            onValueChange = {},
            readOnly = true,
            label = { Text("Categoría") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.name) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun supplierDropdown(
    suppliers: List<Supplier>,
    selectedSupplier: Supplier?,
    onSupplierSelected: (Supplier) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedSupplier?.name ?: "Selecciona un Proveedor",
            onValueChange = {},
            readOnly = true,
            label = { Text("Proveedor") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            suppliers.forEach { supplier ->
                DropdownMenuItem(
                    text = { Text(supplier.name) },
                    onClick = {
                        onSupplierSelected(supplier)
                        expanded = false
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun screenPreview(){
    addProductScreen()
}

