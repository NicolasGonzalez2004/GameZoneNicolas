//maneja toda la lógica del login y el registro.
package com.example.gamezone.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {

    // estado que guarda formualrio login/register
    data class AuthState(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val phone: String = "",
        val genres: List<String> = emptyList(),      // géneros de juegos seleccionados
        val errors: Map<String, String> = emptyMap(),// mensajes de error por campo
        val success: Boolean = false                 // indica si la acción fue exitosa
    )

    // stateflow interno y público para exponer el estado a la ui
    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState

    // actualiza un campo de texto del formulario según el nombre del campo
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

    // agrega o saca un género de la lista (para los checkbox)
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

    // limpia el flag de éxito por si la pantalla lo necesita resetear
    fun resetSuccess() {
        _uiState.update { it.copy(success = false) }
    }

    // lógica de registro: valida campos y marca success si todo está ok
    fun register() {
        val s = _uiState.value
        val errors = mutableMapOf<String, String>()

        // validaciones básicas por campo
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

        // si hay errores, los mandamos a la ui y cortamos
        if (errors.isNotEmpty()) {
            _uiState.update { it.copy(errors = errors, success = false) }
            return
        }


        _uiState.update { it.copy(errors = emptyMap(), success = true) }
    }

    // lógica de login: solo valida el correo y la contraseña
    fun login() {
        val s = _uiState.value
        val errors = mutableMapOf<String, String>()

        // validaciones en cadena, todas guardan el error en la misma clave "login"
        if (s.email.isBlank() || s.password.isBlank()) {
            errors["login"] = "Ingresa correo y contraseña"
        } else if (!s.email.endsWith("@duoc.cl")) {
            errors["login"] = "El correo debe terminar en @duoc.cl"
        } else if (s.password.length < 6) {
            errors["login"] = "Contraseña incorrecta"
        }

        // si hay error se muestra, si no, marcamos éxito
        if (errors.isNotEmpty()) {
            _uiState.update { it.copy(errors = errors, success = false) }
        } else {
            _uiState.update { it.copy(errors = emptyMap(), success = true) }
        }
    }
}
