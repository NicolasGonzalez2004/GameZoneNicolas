package com.example.gamezone.data.remote.randomuser

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RandomUserClient {

    private const val BASE_URL = "https://randomuser.me/"

    val api: RandomUserApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserApi::class.java)
    }
}
