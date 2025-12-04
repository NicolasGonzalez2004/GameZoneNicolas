//interfaz de retrofit que me define los endpoints

package com.example.gamezone.data.remote

import retrofit2.http.*
import com.example.gamezone.data.model.Game

interface GameZoneApi { //interfaaz de las rutas del backend para el retrofit

    @GET("games")
    suspend fun getGames(): List<Game>

    @GET("games/{id}")
    suspend fun getGameById(
        @Path("id") id: Long // me reemplaza el id en la url
    ): Game

    @POST("games")
    suspend fun createGame(
        @Body game: Game // envia un juego como formato  json
    ): Game

    @PUT("games/{id}")
    suspend fun updateGame(
        @Path("id") id: Long,
        @Body game: Game
    ): Game

    @DELETE("games/{id}")
    suspend fun deleteGame(
        @Path("id") id: Long
    )
}
// @path me permite reemplaazar parametros dentro de mi url