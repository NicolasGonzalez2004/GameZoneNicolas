//tests donde verificas que la función que calcula descuentos funcione correctamente con distintos valores.
package com.example.gamezone

import org.junit.Assert.assertEquals
import org.junit.Test

class GamePriceTest {

    // función que calcula un precio aplicando un porcentaje de descuento
    private fun aplicarDescuento(precio: Double, porcentaje: Int): Double {
        return precio - (precio * porcentaje / 100.0)
    }

    @Test
    fun precioConDescuento10porciento() {
        // probamos que un descuento del 10% a 10.000 dé 9.000
        val resultado = aplicarDescuento(10000.0, 10)
        assertEquals(9000.0, resultado, 0.001)
    }

    @Test
    fun precioConDescuento50porciento() {
        // probamos que un descuento del 50% a 20.000 dé 10.000
        val resultado = aplicarDescuento(20000.0, 50)
        assertEquals(10000.0, resultado, 0.001)
    }
}

