package com.example.gamezone.viewmodel

import com.example.gamezone.data.remote.randomuser.RandomUser

data class RandomUserUiState(
    val isLoading: Boolean = false,
    val user: RandomUser? = null,
    val error: String? = null
)
