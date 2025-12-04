//es el cerebro de la pantalla de juegos, controla toda la lógica para obtener y mostrar los juegos
package com.example.gamezone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamezone.data.repository.JuegosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JuegosViewModel(
    private val repository: JuegosRepository = JuegosRepository()
) : ViewModel() {

    // estado que la pantalla observa (loading, juegos y error)
    private val _uiState = MutableStateFlow(JuegosUiState())
    val uiState: StateFlow<JuegosUiState> = _uiState

    fun cargarJuegos() {

        // cuando empieza la carga, activamos loading y limpiamos errores
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null
        )

        // corremos la llamada al repositorio en una corrutina
        viewModelScope.launch {
            try {
                // pedimos los juegos al repositorio
                val lista = repository.obtenerJuegos()

                // si funciona, apagamos loading y guardamos la lista
                _uiState.value = JuegosUiState(
                    isLoading = false,
                    juegos = lista,
                    error = null
                )

            } catch (e: Exception) {

                // si falla algo, mostramos error y dejamos la lista vacía
                _uiState.value = JuegosUiState(
                    isLoading = false,
                    juegos = emptyList(),
                    error = e.message ?: "Error desconocido"
                )
            }
        }
    }
}
