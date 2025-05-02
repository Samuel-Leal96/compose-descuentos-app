package com.example.descuentosapp.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.round

class CalcularViewModel2 : ViewModel() {

    // Va a ser privada con el viewModel y para eso ponemos el private set
    var precio by mutableStateOf("")
        private set

    //Funciones para cuando es un solo textfield
//    fun onValuePrecio(value: String){
//        precio = value
//    }
//    fun onValueDescuento(value: String){
//        descuento = value
//    }

    var descuento by mutableStateOf("")
        private set

    // Funcion para cuando son dos o mas texfields, todo junto en una sola funcion
    fun onValue(value: String, text: String) {
        when (text) {
            "precio" -> precio = value
            "descuento" -> descuento = value
        }
    }

    var precioDescuento by mutableStateOf(0.0)
        private set

    var totalDescuento by mutableStateOf(0.0)
        private set

    var showAlert by mutableStateOf(false)
        private set

    fun calcular() {
        if (precio.isNotEmpty() && descuento.isNotEmpty()) {
            totalDescuento = calcularDescuento(precio.toDouble(), descuento.toDouble())
            precioDescuento = calcularPrecio(precio.toDouble(), descuento.toDouble())
        } else {
            showAlert = true
        }
    }

    private fun calcularPrecio(precio: Double, descuento: Double): Double {
        val res = precio - calcularDescuento(precio, descuento)

        return round(res * 100) / 100
    }

    private fun calcularDescuento(precio: Double, descuento: Double): Double {
        val res = precio * (1 - descuento / 100)

        return round(res * 100) / 100
    }

    fun limpiar() {
        precio = ""
        descuento = ""
        precioDescuento = 0.0
        totalDescuento = 0.0
    }

    fun cancelAlert() {
        showAlert = false
    }


}