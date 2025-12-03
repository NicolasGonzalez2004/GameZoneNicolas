package com.example.gamezone.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.gamezone.data.model.Game
import com.example.gamezone.viewmodel.JuegosViewModel

@Composable
fun JuegosScreen(
    onLogout: () -> Unit,
    onRandomUser: () -> Unit,
    viewModel: JuegosViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    // Cuando entra por primera vez a la pantalla, carga los juegos
    LaunchedEffect(Unit) {
        viewModel.cargarJuegos()
    }

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${state.error}")
            }
        }

        else -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                //  LISTA DE JUEGOS
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    items(state.juegos) { game ->
                        JuegoItem(juego = game)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                //  BOTÓN RANDOM USER (API EXTERNA)
                Button(
                    onClick = onRandomUser,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Ver jugador aleatorio (RandomUser API)")
                }

                Spacer(modifier = Modifier.height(8.dp))

                //  BOTÓN CERRAR SESIÓN
                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Cerrar sesión")
                }
            }
        }
    }
}

@Composable
fun JuegoItem(juego: Game) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Animamos escala y elevación según si está presionado o no
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        label = "cardScale"
    )
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 2.dp else 6.dp,
        label = "cardElevation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            ),
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        shape = RoundedCornerShape(16.dp),
        interactionSource = interactionSource,
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
        ) {

            // Imagen del juego
            AsyncImage(
                model = juego.imageUrl,
                contentDescription = juego.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = juego.title ?: "Juego sin título",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = juego.description ?: "Sin descripción")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Precio: ${juego.price ?: 0.0} CLP")
            }
        }
    }
}
