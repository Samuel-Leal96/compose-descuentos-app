package com.example.descuentosapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.descuentosapp.components.MainButton
import com.example.descuentosapp.components.MainTextField
import com.example.descuentosapp.components.MyAlert
import com.example.descuentosapp.components.SpaceH
import com.example.descuentosapp.components.TwoCards

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "App descuentos", color = Color.White) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }) {
        ContentHomeView(it)
    }
}


@Composable
fun ContentHomeView(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center, Centrar verticalmente
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var precio by rememberSaveable { mutableStateOf("") }
        var descuento by rememberSaveable { mutableStateOf("") }
        var precioDescuento by rememberSaveable { mutableStateOf(0.0) }
        var totalDescuento by rememberSaveable { mutableStateOf(0.0) }

        TwoCards(
            title1 = "Total",
            number1 = totalDescuento,
            title2 = "Descuento%",
            number2 = precioDescuento
        )

        MainTextField(value = precio, onValueChange = { precio = it }, label = "Precio")
        SpaceH()
        MainTextField(value = descuento, onValueChange = { descuento = it }, label = "Descuento%")
        SpaceH(10.dp)
        MainButton(text = "Generar descuento") {
            if(precio.isNotEmpty() && descuento.isNotEmpty()){
                totalDescuento = calcularDescuento(precio.toDouble(), descuento.toDouble())
                precioDescuento = calcularPrecio(precio.toDouble(), descuento.toDouble())
            }else{
                MyAlert(
                    title = "Alerta",
                    message = "Escribe el precio y descuento",
                    confirmText = "Aceptar",
                    onConfirmClick = { }
                ) { }
            }
        }
        SpaceH()
        MainButton(text = "Limpiar", color = Color.Red) {
            precio = ""
            descuento = ""
            totalDescuento = 0.0
            precioDescuento = 0.0

        }

    }
}

fun calcularPrecio(precio: Double, descuento: Double): Double{
    val res = precio - calcularDescuento(precio, descuento)

    return kotlin.math.round(res * 100) / 100
}

fun calcularDescuento(precio: Double, descuento: Double): Double{
    val res = precio * ( 1 - descuento / 100 )

    return kotlin.math.round(res * 100) / 100
}