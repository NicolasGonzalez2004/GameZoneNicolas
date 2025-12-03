package com.example.gamezone.data.model

data class Game(
    val id: Long,
    val title: String,
    val description: String,
    val genre: String,
    val platform: String,
    val price: Double,
    val imageUrl: String
)
