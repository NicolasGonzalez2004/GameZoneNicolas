package com.example.gamezone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamezone.data.remote.randomuser.RandomUserApi
import com.example.gamezone.data.remote.randomuser.RandomUserClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomUserViewModel(
    private val api: RandomUserApi = RandomUserClient.api  // usa tu cliente ya creado
) : ViewModel() {

    private val _uiState = MutableStateFlow(RandomUserUiState())
    val uiState: StateFlow<RandomUserUiState> = _uiState

    fun loadRandomUser() {
        // Poner loading
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null
        )

        viewModelScope.launch {
            try {
                val response = api.getRandomUser()
                val user = response.results.firstOrNull()

                _uiState.value = RandomUserUiState(
                    isLoading = false,
                    user = user,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = RandomUserUiState(
                    isLoading = false,
                    user = null,
                    error = e.message ?: "Error al cargar usuario"
                )
            }
        }
    }
}
