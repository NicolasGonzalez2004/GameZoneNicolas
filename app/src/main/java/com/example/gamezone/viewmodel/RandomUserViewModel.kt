//encargado de pedir un usuario aleatorio a la API y actualizar el estado de la pantalla
package com.example.gamezone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamezone.data.remote.randomuser.RandomUserApi
import com.example.gamezone.data.remote.randomuser.RandomUserClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomUserViewModel(
    private val api: RandomUserApi = RandomUserClient.api  // cliente para llamar a la API
) : ViewModel() {

    // estado interno y el que observa la UI
    private val _uiState = MutableStateFlow(RandomUserUiState())
    val uiState: StateFlow<RandomUserUiState> = _uiState

    fun loadRandomUser() {

        // marcamos que estamos cargando y limpiamos errores
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null
        )

        // corrutina para hacer la llamada sin bloquear la UI
        viewModelScope.launch {
            try {
                // pedimos el usuario aleatorio a la api
                val response = api.getRandomUser()
                val user = response.results.firstOrNull()

                // si llega bien, guardamos el usuario en el estado
                _uiState.value = RandomUserUiState(
                    isLoading = false,
                    user = user,
                    error = null
                )

            } catch (e: Exception) {

                // si ocurre un error, lo informamos y dejamos user en null
                _uiState.value = RandomUserUiState(
                    isLoading = false,
                    user = null,
                    error = e.message ?: "Error al cargar usuario"
                )
            }
        }
    }
}
