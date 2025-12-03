package com.example.farmaciaDrPerez.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun header(modifier: Modifier = Modifier, name:String, color: Color){
    Box(modifier= Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(color),
        contentAlignment = Alignment.Center
    ){
        Text(
            text=name,
            fontSize = 30.sp,
            style=MaterialTheme.typography.titleLarge,
            fontWeight = Bold,
            color=Color.White,
            modifier=Modifier.padding(20.dp)

        )
    }

}

@Preview
@Composable
fun headerPreview(){
    val color:Color =Color(0xFF1D35C4)
    header(
        name="Farmacia Dr. Perez",
        color=color
    )
}