package com.example.gamezone.data.remote.randomuser

// Respuesta principal de la API
data class RandomUserResponse(
    val results: List<RandomUser>
)

// Usuario que viene de la API
data class RandomUser(
    val name: RandomUserName,
    val email: String,
    val picture: RandomUserPicture
)

// Nombre (first + last)
data class RandomUserName(
    val first: String,
    val last: String
)

// Foto del usuario
data class RandomUserPicture(
    val large: String
)
