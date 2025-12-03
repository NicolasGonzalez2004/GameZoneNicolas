package com.example.gamezone.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {

    // estado único para login + registro
    data class AuthState(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val phone: String = "",
        val genres: List<String> = emptyList(),
        val errors: Map<String, String> = emptyMap(),
        val success: Boolean = false
    )

    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState

    //  campos
    fun updateField(field: String, value: String) {
        _uiState.update {
            when (field) {
                "name" -> it.copy(name = value)
                "email" -> it.copy(email = value)
                "password" -> it.copy(password = value)
                "confirmPassword" -> it.copy(confirmPassword = value)
                "phone" -> it.copy(phone = value)
                else -> it
            }
        }
    }

    //  generos (chekbox)
    fun toggleGenre(genre: String) {
        _uiState.update { state ->
            val list = state.genres.toMutableList()
            if (list.contains(genre)) {
                list.remove(genre)
            } else {
                list.add(genre)
            }
            state.copy(genres = list)
        }
    }

    // por si se quiere limpiar el succes
    fun resetSuccess() {
        _uiState.update { it.copy(success = false) }
    }

    //  registro
    fun register() {
        val s = _uiState.value
        val errors = mutableMapOf<String, String>()

        // validaciones
        if (s.name.isBlank()) {
            errors["name"] = "Nombre inválido"
        }
        if (s.email.isBlank() || !s.email.endsWith("@duoc.cl")) {
            errors["email"] = "Correo debe terminar en @duoc.cl"
        }
        if (s.password.length < 6) {
            errors["password"] = "Contraseña debe tener al menos 6 caracteres"
        }
        if (s.password != s.confirmPassword) {
            errors["confirmPassword"] = "Las contraseñas no coinciden"
        }
        if (s.genres.isEmpty()) {
            errors["genres"] = "Selecciona al menos un género"
        }

        if (errors.isNotEmpty()) {
            _uiState.update { it.copy(errors = errors, success = false) }
            return
        }

        // Si todo ok, marcamos success = true
        _uiState.update { it.copy(errors = emptyMap(), success = true) }
    }

    //  login
    fun login() {
        val s = _uiState.value
        val errors = mutableMapOf<String, String>()

        if (s.email.isBlank() || s.password.isBlank()) {
            errors["login"] = "Ingresa correo y contraseña"
        } else if (!s.email.endsWith("@duoc.cl")) {
            errors["login"] = "El correo debe terminar en @duoc.cl"
        } else if (s.password.length < 6) {
            errors["login"] = "Contraseña incorrecta"
        }

        if (errors.isNotEmpty()) {
            _uiState.update { it.copy(errors = errors, success = false) }
        } else {

            _uiState.update { it.copy(errors = emptyMap(), success = true) }
        }
    }
}
