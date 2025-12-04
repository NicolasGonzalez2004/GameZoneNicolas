package com.example.gamezone

import org.junit.Assert.assertEquals
import org.junit.Test

class GamePriceTest {

    // Función sencilla para aplicar un descuento a un precio
    private fun aplicarDescuento(precio: Double, porcentaje: Int): Double {
        return precio - (precio * porcentaje / 100.0)
    }

    @Test
    fun precioConDescuento10porciento() {
        val resultado = aplicarDescuento(10000.0, 10)
        assertEquals(9000.0, resultado, 0.001)
    }

    @Test
    fun precioConDescuento50porciento() {
        val resultado = aplicarDescuento(20000.0, 50)
        assertEquals(10000.0, resultado, 0.001)
    }
}
