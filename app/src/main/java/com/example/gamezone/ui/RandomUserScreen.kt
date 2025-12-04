//pantalla que muestra un usuario aleatorio obtenido desde la API RandomUser.
package com.example.gamezone.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.gamezone.viewmodel.RandomUserViewModel

@Composable
fun RandomUserScreen(
    viewModel: RandomUserViewModel = viewModel(),
    onBack: () -> Unit = {}
) {
    // escuchamos el estado que expone el viewmodel
    val state by viewModel.uiState.collectAsState()

    // al entrar por primera vez a la pantalla pedimos un usuario aleatorio
    LaunchedEffect(Unit) {
        viewModel.loadRandomUser()
    }

    // contenedor principal centrado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {

        when {
            // estado de carga: mostramos spinner
            state.isLoading -> {
                CircularProgressIndicator()
            }

            // si hay error, lo mostramos y damos opción de reintentar
            state.error != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Error: ${state.error}",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = { viewModel.loadRandomUser() }) {
                        Text("Reintentar")
                    }
                }
            }

            // si tenemos usuario, mostramos sus datos
            state.user != null -> {
                val user = state.user!!

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // foto de perfil en círculo
                    AsyncImage(
                        model = user.picture.large,
                        contentDescription = "Foto usuario",
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                    )

                    Spacer(Modifier.height(16.dp))

                    // nombre completo del usuario
                    Text(
                        text = "${user.name.first} ${user.name.last}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    // email del usuario
                    Text(text = "Email: ${user.email}")

                    Spacer(Modifier.height(24.dp))

                    // botón para pedir otro usuario aleatorio
                    Button(onClick = { viewModel.loadRandomUser() }) {
                        Text("Otro usuario aleatorio")
                    }

                    Spacer(Modifier.height(16.dp))

                    // botón para volver a la pantalla de juegos
                    Button(onClick = onBack) {
                        Text("Volver a juegos")
                    }
                }
            }
        }
    }
}
