//estado de la pantalla que muestra un usuario aleatorio
package com.example.gamezone.viewmodel

import com.example.gamezone.data.remote.randomuser.RandomUser

data class RandomUserUiState(
    // indica si se está cargando un usuario desde la api
    val isLoading: Boolean = false,

    // el usuario recibido de randomuser ,null si aún no llega
    val user: RandomUser? = null,

    // mensaje de error en caso de fallo
    val error: String? = null
)
