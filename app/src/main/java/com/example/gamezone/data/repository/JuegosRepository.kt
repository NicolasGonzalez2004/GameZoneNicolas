package com.example.gamezone.data.repository

import com.example.gamezone.data.model.Game
import com.example.gamezone.data.remote.RetrofitClient

open class JuegosRepository {

    private val api = RetrofitClient.api


    open suspend fun obtenerJuegos(): List<Game> {
        return api.getGames()
    }
}
