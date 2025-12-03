package com.example.gamezone.data.repository

import com.example.gamezone.data.model.Game
import com.example.gamezone.data.remote.RetrofitClient

class JuegosRepository {

    // Obtener lista de juegos desde la API
    suspend fun obtenerJuegos(): Result<List<Game>> {
        return try {
            val lista = RetrofitClient.api.getGames()
            Result.success(lista)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Obtener un juego por ID
    suspend fun obtenerJuegoPorId(id: Long): Result<Game> {
        return try {
            val juego = RetrofitClient.api.getGameById(id)
            Result.success(juego)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
