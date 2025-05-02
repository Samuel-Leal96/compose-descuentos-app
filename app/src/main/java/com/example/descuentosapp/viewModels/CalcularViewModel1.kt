package com.example.descuentosapp.viewModels

import androidx.lifecycle.ViewModel
import kotlin.math.round

class CalcularViewModel1: ViewModel() {

    fun calcular(precio: String, descuento: String): Pair<Double, Pair<Double, Boolean>> {
        var precioDescuento = 0.0
        var totalDescuento = 0.0
        var showAlert = false

        if(precio.isNotEmpty() && descuento.isNotEmpty()){
            totalDescuento = calcularDescuento(precio.toDouble(), descuento.toDouble())
            precioDescuento = calcularPrecio(precio.toDouble(), descuento.toDouble())
        }else{
            showAlert = true
        }

        return Pair(precioDescuento, Pair(totalDescuento, showAlert))
    }

    private fun calcularPrecio(precio: Double, descuento: Double): Double{
        val res = precio - calcularDescuento(precio, descuento)

        return round(res * 100) / 100
    }

    private fun calcularDescuento(precio: Double, descuento: Double): Double{
        val res = precio * ( 1 - descuento / 100 )

        return round(res * 100) / 100
    }
}