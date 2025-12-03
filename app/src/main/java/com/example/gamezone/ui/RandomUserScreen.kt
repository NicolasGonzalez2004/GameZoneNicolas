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
    val state by viewModel.uiState.collectAsState()

    // Cuando entro a la pantalla, pido un usuario aleatorio
    LaunchedEffect(Unit) {
        viewModel.loadRandomUser()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

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

            state.user != null -> {
                val user = state.user!!

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // FOTO
                    AsyncImage(
                        model = user.picture.large,
                        contentDescription = "Foto usuario",
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                    )

                    Spacer(Modifier.height(16.dp))

                    // NOMBRE
                    Text(
                        text = "${user.name.first} ${user.name.last}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    // EMAIL
                    Text(text = "Email: ${user.email}")

                    Spacer(Modifier.height(24.dp))

                    Button(onClick = { viewModel.loadRandomUser() }) {
                        Text("Otro usuario aleatorio")
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = onBack) {
                        Text("Volver a juegos")
                    }
                }
            }
        }
    }
}
