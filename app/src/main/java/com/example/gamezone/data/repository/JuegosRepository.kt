// data/repository/JuegosRepository.kt
package com.example.gamezone.data.repository

import com.example.gamezone.data.model.Game
import com.example.gamezone.data.remote.RetrofitClient

open class JuegosRepository {                // <- open
    private val api = RetrofitClient.api

    open suspend fun obtenerJuegos(): List<Game> {   // <- open
        return api.getGames()
    }
}
