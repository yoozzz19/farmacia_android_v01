package com.example.farmaciaDrPerez.components

import com.example.farmaciaDrPerez.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun header(modifier: Modifier = Modifier,logo:String, name:String, color: Color){

    Box(modifier= Modifier
        .fillMaxWidth()
        .height(100.dp),

        Alignment.Center
    ){
       Row (
           modifier= Modifier.padding(15.dp).fillMaxSize(),
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically

       ){
           Box(
            modifier= Modifier.size(50.dp)


           ){
               Image(
                   painter = painterResource(id = R.drawable.logo),
                   contentDescription = "Logo"
               )
           }
           Box(

           ){
               Text(
                   text = name,
                   fontSize = 30.sp,
                   fontWeight = Bold,
                   color = color,
                   modifier = Modifier.padding(15.dp)

               )
           }
       }
    }

}

@Preview
@Composable
fun headerPreview(){
    val color:Color =Color(0xFF1D35C4)
    header(
        logo = "F",
        name="Agregar Producto",
        color=color
    )
}