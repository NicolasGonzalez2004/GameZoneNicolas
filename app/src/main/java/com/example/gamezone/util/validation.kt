package com.example.gamezone.util

object Validation {
    fun isNameValid(name: String) = name.isNotBlank() && name.length in 1..100
    fun isEmailValid(email: String) = email.endsWith("@duoc.cl") && email.length <= 60
    fun isPasswordValid(pw: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%\\^&+=!]).{10,}$")
        return regex.matches(pw)
    }
    fun doPasswordsMatch(pw: String, confirm: String) = pw == confirm
    fun isPhoneValid(phone: String?) = phone.isNullOrBlank() || phone.all { it.isDigit() && phone.length in 7..15 }
}