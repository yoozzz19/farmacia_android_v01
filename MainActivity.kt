package com.example.farmaciaDrPerez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmaciaDrPerez.screens.ReportScreen
import com.example.farmaciaDrPerez.screens.addProductScreen
import com.example.farmaciaDrPerez.ui.theme.FarmaciaDrPerezTheme
import com.example.farmaciaDrPerez.view_models.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val miViewModel = ProductViewModel()
        setContent {
           ReportScreen()
        }
    }
}

