//lugar donde se almacenan y gestionan los usuarios dentro de la app
package com.example.gamezone.data.repository

import com.example.gamezone.data.model.User

// repository simple que maneja una lista en memoria de usuarios
class UserRepository {

    // lista mutable donde se guardan los usuarios registrados ,se puede modificar
    private val users = mutableListOf<User>()

    // función suspend que agrega un usuario a la lista
    suspend fun addUser(user: User) {
        users.add(user) // Añade el usuario a la lista en memoria
    }

    // devuelve todos los usuarios almacenados
    fun getAllUsers() = users
}
