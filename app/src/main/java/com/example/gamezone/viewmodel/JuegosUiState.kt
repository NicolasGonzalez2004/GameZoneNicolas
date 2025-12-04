//representa el estado de la pantalla de juegos.
package com.example.gamezone.viewmodel

import com.example.gamezone.data.model.Game

data class JuegosUiState(
    // indica si se está cargando la lista de juegos
    val isLoading: Boolean = false,

    // lista de juegos que viene del backend (vacía al inicio)
    val juegos: List<Game> = emptyList(),

    // mensaje de error si algo falla al cargar
    val error: String? = null
)

