package com.example.gamezone.data.remote

import retrofit2.http.*
import com.example.gamezone.data.model.Game

interface GameZoneApi {

    @GET("games")
    suspend fun getGames(): List<Game>

    @GET("games/{id}")
    suspend fun getGameById(
        @Path("id") id: Long
    ): Game

    @POST("games")
    suspend fun createGame(
        @Body game: Game
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
