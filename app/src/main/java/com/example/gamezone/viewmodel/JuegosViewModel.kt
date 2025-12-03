package com.example.gamezone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamezone.data.repository.JuegosRepository
import com.example.gamezone.ui.JuegosUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JuegosViewModel(
    private val repository: JuegosRepository = JuegosRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(JuegosUiState())
    val uiState: StateFlow<JuegosUiState> = _uiState

    // Llama a la API
    fun cargarJuegos() {
        // 1. Poner loading en true
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null
        )

        // 2. Llamar al repositorio en corrutina
        viewModelScope.launch {
            val resultado = repository.obtenerJuegos()

            resultado.onSuccess { lista ->
                _uiState.value = JuegosUiState(
                    isLoading = false,
                    juegos = lista,       // ← lista es List<Game>
                    error = null
                )
            }

            resultado.onFailure { e ->
                _uiState.value = JuegosUiState(
                    isLoading = false,
                    juegos = emptyList(),
                    error = e.message ?: "Error desconocido"
                )
            }
        }
    }
}
