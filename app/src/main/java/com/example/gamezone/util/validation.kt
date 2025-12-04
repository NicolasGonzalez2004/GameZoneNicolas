//objeto que agrupa todas las funciones para validar datos del usuario.
package com.example.gamezone.util

object Validation {

    // valida que el nombre no esté vacío y tenga un largo razonable
    fun isNameValid(name: String) = name.isNotBlank() && name.length in 1..100

    // solo acepta correos que terminen en @duoc.cl y con límite de 60 caracteres
    fun isEmailValid(email: String) = email.endsWith("@duoc.cl") && email.length <= 60

    // contraseña con mayuscula, minuscula, número, símbolo y mínimo 10 caracteres
    fun isPasswordValid(pw: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%\\^&+=!]).{10,}$")
        return regex.matches(pw)
    }

    // compara que ambas contraseñas sean iguales
    fun doPasswordsMatch(pw: String, confirm: String) = pw == confirm

    // el teléfono es opcional. si viene, debe tener solo números y largo entre 7 y 15
    fun isPhoneValid(phone: String?) =
        phone.isNullOrBlank() || phone.all { it.isDigit() && phone.length in 7..15 }
}
