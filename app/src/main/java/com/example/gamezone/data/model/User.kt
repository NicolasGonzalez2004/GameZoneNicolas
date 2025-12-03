package com.example.gamezone.data.model

data class User(
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val phone: String?,
    val genres: List<String>
)

