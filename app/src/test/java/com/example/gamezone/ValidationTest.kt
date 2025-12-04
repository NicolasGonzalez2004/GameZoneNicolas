//tests que verifica que la función validarCorreo funcione bien para correos válidos e inválidos.
package com.example.gamezone

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationTest {

    // función simple que revisa si un correo tiene "@" y "."
    private fun validarCorreo(correo: String): Boolean {
        return correo.contains("@") && correo.contains(".")
    }

    @Test
    fun correoValido_devuelveTrue() {
        // probamos que un correo bien formado sea válido
        val resultado = validarCorreo("test@example.com")
        assertTrue(resultado)
    }

    @Test
    fun correoInvalido_devuelveFalse() {
        // probamos que un correo sin "@" o "." sea inválido
        val resultado = validarCorreo("testexample.com")
        assertFalse(resultado)
    }
}



