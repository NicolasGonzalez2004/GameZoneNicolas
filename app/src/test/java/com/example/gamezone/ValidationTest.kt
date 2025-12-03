package com.example.gamezone

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationTest {

    // Función sencilla para validar un correo
    private fun validarCorreo(correo: String): Boolean {
        return correo.contains("@") && correo.contains(".")
    }

    @Test
    fun correoValido_devuelveTrue() {
        val resultado = validarCorreo("test@example.com")
        assertTrue(resultado)
    }

    @Test
    fun correoInvalido_devuelveFalse() {
        val resultado = validarCorreo("testexample.com")
        assertFalse(resultado)
    }
}


