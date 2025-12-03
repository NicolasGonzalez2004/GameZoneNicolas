package com.example.gamezone.data.remote.randomuser

import retrofit2.http.GET

interface RandomUserApi {


    @GET("api")
    suspend fun getRandomUser(): RandomUserResponse
}
