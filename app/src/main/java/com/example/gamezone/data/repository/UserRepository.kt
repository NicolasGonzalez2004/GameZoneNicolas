package com.example.gamezone.data.repository

import com.example.gamezone.data.model.User

class UserRepository {
    private val users = mutableListOf<User>()

    suspend fun addUser(user: User) {
        users.add(user)
    }

    fun getAllUsers() = users
}