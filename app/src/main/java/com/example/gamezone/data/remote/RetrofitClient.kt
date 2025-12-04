//es mi objeto que configura retrofit

package com.example.gamezone.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8080/" // es mi url del backend


    val api: GameZoneApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // esto me define la url
            .addConverterFactory(GsonConverterFactory.create()) // me convierte json los datos del backend json en objetos kotlin
            .build() //contruye retrofit
            .create(GameZoneApi::class.java) // me crea la implementacion de gamezonapi
    }
}
