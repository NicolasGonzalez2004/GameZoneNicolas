package com.example.gamezone.ui

import com.example.gamezone.data.model.Game

data class JuegosUiState(
    val isLoading: Boolean = false,
    val juegos: List<Game> = emptyList(),
    val error: String? = null
)
